package mars.temporence.api.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.user.event.dto.RequestTokenDto;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.global.dto.TokenDto;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
import mars.temporence.global.jwt.JwtTokenProvider;
import mars.temporence.global.jwt.JwtTokenValidator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository userJpaRepository;
    private final JwtTokenValidator jwtTokenValidator;
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto, UserDetailDto userDetailDto) throws Exception {
        jwtTokenValidator.validateToken(dto.getRefreshToken());

        Optional<User> findUser = userJpaRepository.findById(userDetailDto.getUserId());

        if (findUser.isEmpty()) {
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        String accessToken = stringRedisTemplate.opsForValue().get("access" + findUser.get().getId());
        String refreshToken = stringRedisTemplate.opsForValue().get("refresh" + findUser.get().getId());


        if (refreshToken.isEmpty()) {
            throw new BadRequestException("유저가 로그인 상태가 아닙니다.");
        }

        if (!refreshToken.equals(dto.getRefreshToken())) {
            throw new BadRequestException("유저 정보가 일치하지 않습니다.");
        }



        TokenDto tokenDto = jwtTokenProvider.issueToken(findUser.get().getId(), findUser.get().getAuthority());

        if (accessToken == null) {
            stringRedisTemplate.delete("access" + findUser.get().getId());
        }

        if (refreshToken == null) {
            stringRedisTemplate.delete("refresh" + findUser.get().getId());
        }

        stringRedisTemplate.opsForValue().set("access" + findUser.get().getId(), tokenDto.getAccessToken());
        stringRedisTemplate.opsForValue().set("refresh" + findUser.get().getId(), tokenDto.getAccessToken());

        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "토큰 재발급에 성공 하였습니다.", tokenDto);
    }
}
