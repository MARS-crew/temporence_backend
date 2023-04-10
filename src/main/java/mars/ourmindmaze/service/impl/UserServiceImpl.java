package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.RequestResponseDto;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.domain.RefreshToken;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.RequestLoginUserDto;
import mars.ourmindmaze.dto.RequestSaveUserDto;
import mars.ourmindmaze.dto.RequestTokenDto;
import mars.ourmindmaze.enums.UserType;
import mars.ourmindmaze.jwt.TokenDto;
import mars.ourmindmaze.jwt.TokenProvider;
import mars.ourmindmaze.repository.RefreshJpaTokenRepository;
import mars.ourmindmaze.repository.UserJpaRepository;
import mars.ourmindmaze.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final RefreshJpaTokenRepository refreshJpaTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public RequestResponseDto<?> save(RequestSaveUserDto dto) {
        try {
            Optional<User> findUser = userJpaRepository.findUserByEmail(dto.getEmail());
            if (findUser.isPresent()) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "이미 존재 하는 계정 입니다.", false);
            }

            User saveUser = User.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .name(dto.getName())
                    .userType(UserType.LOCAL)
                    .authority(UserAuthority.ROLE_USER)
                    .build();

            return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "회원가입 성공", userJpaRepository.save(saveUser));
        } catch (Exception e) {
            logger.info("ERROR :" + e);
            return RequestResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, RequestResponseDto.Code.FAILED, e.getMessage(), null);
        }
    }

    @Override
    public RequestResponseDto<?> login(RequestLoginUserDto dto) {
        try {
            Optional<User> findUser = userJpaRepository.findUserByEmail(dto.getEmail());

            if (findUser.isEmpty()) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "사용자를 찾을 수 없습니다.", false);
            }

            if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "비밀번호가 같지 앖습니다.", false);
            }
            UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Optional<RefreshToken> refreshToken = refreshJpaTokenRepository.findByAuthKeyAndType(authentication.getName(), "user");

            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            if (refreshToken.isPresent()) {
                refreshToken.get().updateValue(tokenDto.getRefreshToken());
            } else {
                refreshJpaTokenRepository.save(RefreshToken.refreshTokenBuilder()
                        .authKey(authentication.getName())
                        .authValue(tokenDto.getRefreshToken())
                        .type("user")
                        .build());
            }

            Map<String, String> response = new HashMap<>();

            response.put("accessToken", tokenDto.getAccessToken());
            response.put("refreshToken", tokenDto.getRefreshToken());
            response.put("id", String.valueOf(findUser.get().getId()));
            response.put("email", findUser.get().getEmail());
            response.put("name", findUser.get().getName());
            return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "로그인 성공 하였습니다.", response);
        } catch (Exception e) {
            logger.info("ERROR :" + e);
            return RequestResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, RequestResponseDto.Code.FAILED, e.getMessage(), null);
        }
    }

    public RequestResponseDto<?> getTokenByRefreshToken(RequestTokenDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "만료된 RefreshToken 입니다.", false);
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        Optional<RefreshToken> refreshToken = refreshJpaTokenRepository.findByAuthKeyAndType(authentication.getName(), "user");

        if (refreshToken.isEmpty()) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "로그아웃 된 사용자 입니다.", false);
        }

        if (!refreshToken.get().getAuthValue().equals(tokenRequestDto.getRefreshToken())) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "토큰의 유저 정보가 일치하지 않습니다.", false);
        }

        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "만료된 RefreshToken 입니다.", false);
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        refreshJpaTokenRepository.save(refreshToken.get().updateValue(tokenDto.getRefreshToken()));

        return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "토큰 재발급에 성공 하였습니다.", tokenDto);
    }
}
