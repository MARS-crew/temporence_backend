package mars.ourmindmaze.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    EXPIRED_JWT_EXCEPTION(HttpStatus.CONFLICT,"이미 존재하는 계정 입니다.",0),
    ;

    private final HttpStatus status;
    private String message;
    private Integer errorCode;

    ExceptionEnum(HttpStatus status){
        this.status=status;
    }

    ExceptionEnum(HttpStatus status, String message, Integer errorCode){
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
