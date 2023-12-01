package mars.temporence.api.user.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.global.dto.Pagination;
import mars.temporence.global.enums.UserAuthority;
import mars.temporence.api.point.domain.Point;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.user.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.user.event.dto.RequestUserLoginDto;
import mars.temporence.api.user.event.dto.RequestUserSaveDto;
import mars.temporence.api.user.event.dto.RequestTokenDto;
import mars.temporence.global.dto.TokenDto;
import mars.temporence.api.point.repository.PointJpaRepository;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.api.user.event.vo.UserVO;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
import mars.temporence.global.jwt.JwtTokenProvider;
import mars.temporence.global.jwt.JwtTokenValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> save(RequestUserSaveDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (!findUser.isEmpty()) {
            logger.warn("=== 사용 중인 이메일 입니다. ===");
            throw new BadRequestException("사용 중인 이메일 입니다.");
        }

        Optional<User> findUserByNickname = userJpaRepository.findByNickname(dto.getNickname());

        if (!findUserByNickname.isEmpty()) {
            logger.warn("=== 사용 중인 닉네임 입니다. ===");
            throw new BadRequestException("사용 중인 닉네임 입니다.");
        }

        User saveUser = userJpaRepository.save(User.builder().nickname(dto.getNickname()).username(dto.getUsername()).password(passwordEncoder.encode(dto.getPassword())).authority(UserAuthority.ROLE_USER).build());

        pointJpaRepository.save(Point.builder().blue(0).gold(0).user(saveUser).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "회원가입에 성공하였습니다..");
    }

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) throws Exception {
        Page<UserVO> response = userJpaRepository.findAllUser(pageable);
        List<UserVO> data = response.getContent();
        Pagination pagination = Pagination.builder().totalPages(response.getTotalPages()).currentPage(response.getNumber()).totalItems(response.getTotalElements()).build();

        return CommonResponse.createResponseWithPagination(HttpStatus.OK.value(), "유저 리스트를 조회 합니다.", data, pagination);
    }

    @Override
    public ResponseEntity<?> login(RequestUserLoginDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (findUser.isEmpty()) {
            logger.warn("=== 유저를 찾을 수 없습니다. ===");
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
            logger.warn("=== 패스워드가 일치하지 않습니다. ===");
            throw new BadRequestException("패스워드가 일치하지 않습니다.");
        }

        String accessToken = stringRedisTemplate.opsForValue().get("access" + findUser.get().getId());
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + findUser.get().getId());

        if (accessToken != null) {
            logger.warn("=== Redis에 Access Token이 있습니다. ===");
            stringRedisTemplate.delete("access" + findUser.get().getId());
        }

        if (refreshToken != null) {
            logger.warn("=== Redis에 Refresh Token이 있습니다. ===");
            stringRedisTemplate.delete("refresh" + findUser.get().getId());
        }

        TokenDto tokenDto = jwtTokenProvider.issueToken(findUser.get().getId(), findUser.get().getAuthority());

        stringRedisTemplate.opsForValue().set("access" + findUser.get().getId(), tokenDto.getAccessToken());
        stringRedisTemplate.opsForValue().set("refresh" + findUser.get().getId(), tokenDto.getRefreshToken());
        Map<String, String> response = new HashMap<>();

        response.put("accessToken", tokenDto.getAccessToken());
        response.put("refreshToken", tokenDto.getRefreshToken());
        response.put("id", String.valueOf(findUser.get().getId()));
        response.put("email", findUser.get().getUsername());
        response.put("nickname", findUser.get().getNickname());
        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "로그인에 성공하였습니다.", response);
    }
    @Override
    public ResponseEntity<?> existNickname(RequestNicknameCheckDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByNickname(dto.getNickname());

        if (!findUser.isEmpty()) {
            throw new BadRequestException("사용 중인 닉네임 입니다.");
        }

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "사용 가능한 닉네임 입니다.");
    }
}
