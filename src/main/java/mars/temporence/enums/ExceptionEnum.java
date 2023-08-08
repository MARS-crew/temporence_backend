package mars.temporence.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    ;

    private final HttpStatus status;
    private String message;
    private Integer errorCode;

    ExceptionEnum(HttpStatus status, String message, Integer errorCode){
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
