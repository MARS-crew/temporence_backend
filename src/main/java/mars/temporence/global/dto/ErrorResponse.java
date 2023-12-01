package mars.temporence.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final int code;
    private final HttpStatus status;
}
