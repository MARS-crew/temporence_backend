package mars.temporence.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.ErrorMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;
    private StringRedisTemplate stringRedisTemplate;

    public JwtAuthenticationFilter(final TokenProvider tokenProvider, final StringRedisTemplate stringRedisTemplate) {
        super();
        this.tokenProvider = tokenProvider;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Optional<String> optToken = tokenProvider.resolveToken(request);
        try {
            if (optToken.isPresent() && tokenProvider.validateToken(optToken.get())) {
                logger.info("==== JWT : " + optToken.get() + " =====");
                Authentication auth = tokenProvider.getAuthentication(optToken.get());
                if (auth != null) {
                    try {
                        logger.info("===== " + auth.getName() + " =====");
                        String accessToken = stringRedisTemplate.opsForValue().get("access" + auth.getName());

                        if (!accessToken.equals(optToken.get())) {
                            ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
                                    .code(ErrorMessage.TOKEN_EXPIRED.getCode()).message("중복 로그인 입니다.")
                                    .init().writeJson(response);
                        }

                        logger.info("===== Access Token is Duplicated =====");
                        logger.info("===== " + accessToken + " =====");
                    } catch (NullPointerException e) {
                        logger.info("===== Token is Empty in Redis =====");
                    }
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(new RequestWrapper(request), response);
                }
            } else {
                filterChain.doFilter(new RequestWrapper(request), response);
            }


        } catch (JwtException e) {
            if (e instanceof ExpiredJwtException) {
                ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
                        .code(ErrorMessage.TOKEN_EXPIRED.getCode()).message(ErrorMessage.TOKEN_EXPIRED.getMessage())
                        .init().writeJson(response);
            } else {
                ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
                        .code(ErrorMessage.TOKEN_INVALID.getCode()).message(ErrorMessage.TOKEN_INVALID.getMessage())
                        .init().writeJson(response);
            }
        } catch (Exception e) {
            logger.warn("===== FILTER ERROR =====");
            logger.warn(e);
            ApiResponse.<Object>builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .code(ErrorMessage.API500001.getCode()).message(ErrorMessage.API500001.getMessage())
                    .init().writeJson(response);
        }
    }
}