package mars.temporence.jwt;

import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.info("Unauthorized error: {}", authException.getMessage());
        ApiResponse.<Object>builder().status(HttpStatus.UNAUTHORIZED.value())
                .code(ErrorMessage.UNAUTHORIZED.getCode()).message(ErrorMessage.UNAUTHORIZED.getMessage())
                .init().writeJson(response);
    }
}
