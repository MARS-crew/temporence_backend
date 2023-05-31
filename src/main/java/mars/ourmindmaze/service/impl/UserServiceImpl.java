package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.common.dto.Pagination;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.domain.Point;
import mars.ourmindmaze.domain.RefreshToken;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import mars.ourmindmaze.enums.ExceptionEnum;
import mars.ourmindmaze.enums.SocialType;
import mars.ourmindmaze.jwt.TokenDto;
import mars.ourmindmaze.jwt.TokenProvider;
import mars.ourmindmaze.repository.PointJpaRepository;
import mars.ourmindmaze.repository.RefreshRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.UserService;
import mars.ourmindmaze.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final RefreshRepository refreshRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public ResponseEntity<?> save(RequestUserSaveDto dto) {
        Optional<User> findUser = userJpaRepository.findByUsername(dto.getUsername());
        if (!findUser.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.EXIST_EMAIL).buildObject();
        }

        User saveUser = userJpaRepository.save(User.builder().username(dto.getUsername()).password(passwordEncoder.encode(dto.getPassword())).socialType(SocialType.LOCAL).authority(UserAuthority.ROLE_USER).build());

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
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_FOUDN_USER).buildObject();
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.PASSWORD_WRONG).buildObject();
        }
        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<RefreshToken> refreshToken = refreshRepository.findByUsername(authentication.getName());

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        if (refreshToken.isPresent()) {
            refreshRepository.delete(refreshToken.get());
        }

        refreshRepository.save(new RefreshToken(tokenDto.getRefreshToken(), authentication.getName()));

        Map<String, String> response = new HashMap<>();

        response.put("accessToken", tokenDto.getAccessToken());
        response.put("refreshToken", tokenDto.getRefreshToken());
        response.put("id", String.valueOf(findUser.get().getId()));
        response.put("email", findUser.get().getUsername());
        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "로그인에 성공하였습니다.", response);
    }

    public ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.TOKEN_WRONG_DATE).buildObject();
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        Optional<RefreshToken> refreshToken = refreshRepository.findById(tokenRequestDto.getRefreshToken());

        if (refreshToken.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.USER_NOT_LOGIN).buildObject();
        }

        if (!refreshToken.get().getId().equals(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_SAME_USER).buildObject();
        }

        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.TOKEN_WRONG_DATE).buildObject();
        }

        TokenDto response = tokenProvider.generateTokenDto(authentication);

        refreshRepository.delete(refreshToken.get());

        refreshRepository.save(new RefreshToken(response.getRefreshToken(), authentication.getName()));

        return CommonResponse.createResponse(HttpStatus.CREATED.value(), "토큰 재발급에 성공 하였습니다.", response);
    }
}
