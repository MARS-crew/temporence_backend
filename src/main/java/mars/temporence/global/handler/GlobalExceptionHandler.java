package mars.temporence.global.handler;

import io.jsonwebtoken.JwtException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.global.dto.ErrorResponse;
import mars.temporence.global.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.*;

/**
 * 전역 에러 처리 핸들러
 */
@Slf4j
@NoArgsConstructor
@RestControllerAdvice(basePackages = {"boilerplate.pinomaker"})
public class GlobalExceptionHandler {
    @ExceptionHandler({
            JwtException.class,
            UnAuthenticationException.class
    })
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(Exception e) {
        return errorResponse(e, UNAUTHORIZED);
    }

    @ExceptionHandler({
            BadRequestException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        return errorResponse(e, BAD_REQUEST);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ValidationException.class,
    })
    public ResponseEntity<ErrorResponse> handleValidationException(Exception e) {
        log.error("[LOG] {} ({}) : {}", BAD_REQUEST, BAD_REQUEST.value(), "입력값을 확인해주세요.");
        return new ResponseEntity<>(new ErrorResponse("입력값을 확인해주세요.", BAD_REQUEST.value(), BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ErrorResponse> handleForbiddenException(Exception e) {
        return errorResponse(e, FORBIDDEN);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        return errorResponse(e, NOT_FOUND);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e) {
        return errorResponse(e, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerException.class)
    protected ResponseEntity<ErrorResponse> handleServerException(final ServerException e) {
        return errorResponse(e, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> errorResponse(Exception e, HttpStatus httpStatus) {
        log.error("[LOG] {} ({}) : {}", httpStatus, httpStatus.value(), e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), httpStatus.value(), httpStatus), httpStatus);
    }
}
