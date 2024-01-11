package mars.temporence.api.auth.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.point.domain.Point;
import mars.temporence.api.point.repository.PointJpaRepository;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.auth.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.auth.event.dto.RequestTokenDto;
import mars.temporence.api.auth.event.dto.RequestUserLoginDto;
import mars.temporence.api.auth.event.dto.RequestUserSaveDto;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.global.dto.TokenDto;
import mars.temporence.global.enums.UserAuthority;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
import mars.temporence.global.jwt.JwtTokenProvider;
import mars.temporence.global.jwt.JwtTokenValidator;
import mars.temporence.global.util.EncryptionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository userJpaRepository;
    private final JwtTokenValidator jwtTokenValidator;
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final PointJpaRepository pointJpaRepository;
    private final EncryptionUtils encryptionUtils;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> save(RequestUserSaveDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (!findUser.isEmpty()) {
            log.warn("=== 사용 중인 이메일 입니다. ===");
            throw new BadRequestException("사용 중인 이메일 입니다.");
        }

        Optional<User> findUserByNickname = userJpaRepository.findByNickname(dto.getNickname());

        if (!findUserByNickname.isEmpty()) {
            log.warn("=== 사용 중인 닉네임 입니다. ===");
            throw new BadRequestException("사용 중인 닉네임 입니다.");
        }

        User saveUser = userJpaRepository.save(User.builder().nickname(dto.getNickname()).username(dto.getUsername()).password(passwordEncoder.encode(dto.getPassword())).authority(UserAuthority.ROLE_USER).build());

        pointJpaRepository.save(Point.builder().blue(0).gold(0).user(saveUser).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "회원가입에 성공하였습니다..");
    }

    @Override
    public ResponseEntity<?> login(RequestUserLoginDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());

        if (findUser.isEmpty()) {
            log.warn("=== 유저를 찾을 수 없습니다. ===");
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
            log.warn("=== 패스워드가 일치하지 않습니다. ===");
            throw new BadRequestException("패스워드가 일치하지 않습니다.");
        }

        String accessToken = stringRedisTemplate.opsForValue().get("access" + findUser.get().getId());
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + findUser.get().getId());

        if (accessToken != null) {
            log.warn("=== Redis에 Access Token이 있습니다. ===");
            stringRedisTemplate.delete("access" + findUser.get().getId());
        }

        if (refreshToken != null) {
            log.warn("=== Redis에 Refresh Token이 있습니다. ===");
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

    @Override
    public ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto) throws Exception {
        jwtTokenValidator.validateToken(dto.getRefreshToken());

        // ** 유저 정보 추출
        Claims claims = jwtTokenValidator.getClaimsFromToken(dto.getRefreshToken());

        String encodedUserId = String.valueOf(claims.get("id"));
        String encodedRole = String.valueOf(claims.get("role"));

        // ** 정보 디코딩
        Long decodedUserId = Long.valueOf(encryptionUtils.decrypt(encodedUserId));
        UserAuthority decodedRole = UserAuthority.valueOf(encryptionUtils.decrypt(encodedRole));

        String accessToken = stringRedisTemplate.opsForValue().get("access" + decodedUserId);
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + decodedUserId);

        if (refreshToken.isEmpty()) {
            throw new BadRequestException("유저가 로그인 상태가 아닙니다.");
        }

        if (!refreshToken.equals(dto.getRefreshToken())) {
            throw new BadRequestException("유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = jwtTokenProvider.issueToken(decodedUserId, decodedRole);

        if (accessToken == null) {
            stringRedisTemplate.delete("access" + decodedUserId);
        }

        if (refreshToken == null) {
            stringRedisTemplate.delete("refresh" + decodedUserId);
        }

        stringRedisTemplate.opsForValue().set("access" + decodedUserId, tokenDto.getAccessToken());
        stringRedisTemplate.opsForValue().set("refresh" + decodedUserId, tokenDto.getRefreshToken());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "토큰 재발급에 성공 하였습니다.", tokenDto);
    }
}
