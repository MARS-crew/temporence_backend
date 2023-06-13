package mars.ourmindmaze.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;

    public JwtAuthenticationFilter(final TokenProvider tokenProvider) {
        super();
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        Optional<String> optToken = tokenProvider.resolveToken(request);
        try {
            if (optToken.isPresent() && tokenProvider.validateToken(optToken.get())) {
                Authentication auth = tokenProvider.getAuthentication(optToken.get());
                if (auth != null) {
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
            ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
                    .code(ErrorMessage.TOKEN_VALIDATE.getCode()).message(ErrorMessage.TOKEN_INVALID.getMessage())
                    .init().writeJson(response);

        }

    }
}