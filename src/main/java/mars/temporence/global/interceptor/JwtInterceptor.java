package mars.temporence.global.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.exception.UnAuthenticationException;
import mars.temporence.global.jwt.JwtTokenExtractor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtTokenExtractor jwtTokenExtractor;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(request);
        request.setAttribute("user", userDetailDto);

        String jwt = jwtTokenExtractor.getAuthorizationToken(request);
        String accessToken = stringRedisTemplate.opsForValue().get("access" + userDetailDto.getUserId());

        if (!accessToken.equals(jwt)) {
            throw new UnAuthenticationException("중복 로그인 입니다.");
        }

        log.info("USER ID : {}", userDetailDto.toString());

        return true;
    }
}
//
//    private StringRedisTemplate stringRedisTemplate;
//
//    public JwtAuthenticationFilter(final TokenProvider tokenProvider, final StringRedisTemplate stringRedisTemplate) {
//        super();
//        this.tokenProvider = tokenProvider;
//        this.stringRedisTemplate = stringRedisTemplate;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        Optional<String> optToken = tokenProvider.resolveToken(request);
//        try {
//            if (optToken.isPresent() && tokenProvider.validateToken(optToken.get())) {
//                logger.info("==== JWT : " + optToken.get() + " =====");
//                Authentication auth = tokenProvider.getAuthentication(optToken.get());
//                if (auth != null) {
//                    try {
//                        logger.info("===== " + auth.getName() + " =====");
//                        String accessToken = stringRedisTemplate.opsForValue().get("access" + auth.getName());
//
//                        if (!accessToken.equals(optToken.get())) {
//                            ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
//                                    .code(ErrorMessage.TOKEN_EXPIRED.getCode()).message("중복 로그인 입니다.")
//                                    .init().writeJson(response);
//                        }
//
//                        logger.info("===== Access Token is Duplicated =====");
//                        logger.info("===== " + accessToken + " =====");
//                    } catch (NullPointerException e) {
//                        logger.info("===== Token is Empty in Redis =====");
//                    }
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                    filterChain.doFilter(new RequestWrapper(request), response);
//                }
//            } else {
//                filterChain.doFilter(new RequestWrapper(request), response);
//            }
