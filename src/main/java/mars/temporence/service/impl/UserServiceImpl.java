package mars.temporence.service.impl;

import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.CommonResponse;
import mars.temporence.common.dto.Pagination;
import mars.temporence.common.dto.UserAuthority;
import mars.temporence.domain.Point;
import mars.temporence.domain.User;
import mars.temporence.dto.user.RequestNicknameCheckDto;
import mars.temporence.dto.user.RequestUserLoginDto;
import mars.temporence.dto.user.RequestUserSaveDto;
import mars.temporence.dto.user.RequestTokenDto;
import mars.temporence.jwt.TokenDto;
import mars.temporence.jwt.TokenProvider;
import mars.temporence.repository.PointJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import mars.temporence.service.UserService;
import mars.temporence.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final PointJpaRepository pointJpaRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public ResponseEntity<?> save(RequestUserSaveDto dto) {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (!findUser.isEmpty()) {
            logger.warn("=== 사용 중인 이메일 입니다. ===");
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("사용 중인 이메일 입니다.").buildObject();
        }

        Optional<User> findUserByNickname = userJpaRepository.findByNickname(dto.getNickname());

        if (!findUserByNickname.isEmpty()) {
            logger.warn("=== 사용 중인 닉네임 입니다. ===");
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("사용 중인 닉네임 입니다.").buildObject();
        }

        User saveUser = userJpaRepository.save(User.builder().nickname(dto.getNickname()).username(dto.getUsername()).password(passwordEncoder.encode(dto.getPassword())).authority(UserAuthority.ROLE_USER).build());

        pointJpaRepository.save(Point.builder().blue(0).gold(0).user(saveUser).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "회원가입에 성공하였습니다..");
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<UserVO> response = userJpaRepository.findAllUser(pageable);
        List<UserVO> data = response.getContent();
        Pagination pagination = Pagination.builder().totalPages(response.getTotalPages()).currentPage(response.getNumber()).totalItems(response.getTotalElements()).build();

        return CommonResponse.createResponseWithPagination(HttpStatus.OK.value(), "유저 리스트를 조회 합니다.", data, pagination);
    }

    @Override
    public ResponseEntity<?> login(RequestUserLoginDto dto) {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (findUser.isEmpty()) {
            logger.warn("=== 유저를 찾을 수 없습니다. ===");
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
            logger.warn("=== 패스워드가 일치하지 않습니다. ===");
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("패스워드가 일치하지 않습니다.").buildObject();
        }
        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String accessToken = stringRedisTemplate.opsForValue().get("access" + findUser.get().getUsername());
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + findUser.get().getUsername());

        if (accessToken != null) {
            logger.warn("=== Redis에 Access Token이 있습니다. ===");
            stringRedisTemplate.delete("access" + findUser.get().getUsername());
        }

        if (refreshToken != null) {
            logger.warn("=== Redis에 Refresh Token이 있습니다. ===");
            stringRedisTemplate.delete("refresh" + findUser.get().getUsername());
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        stringRedisTemplate.opsForValue().set("access" + findUser.get().getUsername(), tokenDto.getAccessToken());
        stringRedisTemplate.opsForValue().set("refresh" + findUser.get().getUsername(), tokenDto.getRefreshToken());
        Map<String, String> response = new HashMap<>();

        response.put("accessToken", tokenDto.getAccessToken());
        response.put("refreshToken", tokenDto.getRefreshToken());
        response.put("id", String.valueOf(findUser.get().getId()));
        response.put("email", findUser.get().getUsername());
        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "로그인에 성공하였습니다.", response);
    }

    public ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("토큰이 유효하지 않습니다.").buildObject();
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        Optional<User> findUser = userJpaRepository.findByUsername(authentication.getName());

        if (findUser.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저 정보를 찾을 수 없습니다.").buildObject();
        }

        String accessToken = stringRedisTemplate.opsForValue().get("access" + findUser.get().getUsername());
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + findUser.get().getUsername());


        if (refreshToken.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("유저가 로그인 상태가 아닙니다.").buildObject();
        }

        if (!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("유저 정보가 일치하지 않습니다.").buildObject();
        }

        TokenDto response = tokenProvider.generateTokenDto(authentication);

        if (accessToken == null) {
            stringRedisTemplate.delete("access" + findUser.get().getUsername());
        }

        if (refreshToken == null) {
            stringRedisTemplate.delete("refresh" + findUser.get().getUsername());
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        stringRedisTemplate.opsForValue().set("access" + findUser.get().getUsername(), tokenDto.getAccessToken());
        stringRedisTemplate.opsForValue().set("refresh" + findUser.get().getUsername(), tokenDto.getAccessToken());

        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "토큰 재발급에 성공 하였습니다.", response);
    }

    @Override
    public ResponseEntity<?> existNickname(RequestNicknameCheckDto dto) {
        Optional<User> findUser = userJpaRepository.findByNickname(dto.getNickname());

        if (!findUser.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("사용 중인 닉네임 입니다.").buildObject();
        }

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "사용 가능한 닉네임 입니다.");
    }
}
