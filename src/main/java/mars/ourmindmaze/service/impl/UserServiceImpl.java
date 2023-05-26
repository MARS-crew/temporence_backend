package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.domain.RefreshToken;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import mars.ourmindmaze.enums.ExceptionEnum;
import mars.ourmindmaze.enums.UserType;
import mars.ourmindmaze.jwt.TokenDto;
import mars.ourmindmaze.jwt.TokenProvider;
import mars.ourmindmaze.repository.RefreshJpaTokenRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(RequestUserSaveDto dto) {
        Optional<User> findUser = userJpaRepository.findUserByEmail(dto.getEmail());
        if (!findUser.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.EXIST_EMAIL).buildObject();
        }

        userJpaRepository.save(User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .userType(UserType.LOCAL)
                .authority(UserAuthority.ROLE_USER)
                .build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "회원가입에 성공하였습니다..");
    }

//    @Override
//    public ResponseEntity<?> findAll() {
//        return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "로그인 성공 하였습니다.", userJpaRepository.findAll());
//    }

    @Override
    public ResponseEntity<?> login(RequestUserLoginDto dto) {
        Optional<User> findUser = userJpaRepository.findUserByEmail(dto.getEmail());

        if (findUser.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_FOUDN_USER).buildObject();
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.PASSWORD_WRONG).buildObject();
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
        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "로그인에 성공하였습니다.", response);
    }

    public ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.TOKEN_WRONG_DATE).buildObject();
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        Optional<RefreshToken> refreshToken = refreshJpaTokenRepository.findByAuthKeyAndType(authentication.getName(), "user");

        if (refreshToken.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.USER_NOT_LOGIN).buildObject();
        }

        if (!refreshToken.get().getAuthValue().equals(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_SAME_USER).buildObject();
        }

        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.TOKEN_WRONG_DATE).buildObject();
        }

        TokenDto response = tokenProvider.generateTokenDto(authentication);

        refreshJpaTokenRepository.save(refreshToken.get().updateValue(response.getRefreshToken()));

        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "토큰 재발급에 성공 하였습니다.", response);
    }
}
