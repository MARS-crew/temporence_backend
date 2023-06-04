package mars.ourmindmaze.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용하고 있는 이메일 입니다.", 0),
    NOT_FOUDN_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.", 1),
    PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "패스워드가 틀렸습니다.", 2),
    TOKEN_WRONG_DATE(HttpStatus.BAD_REQUEST, "토큰의 만료시간이 지났습니다.", 3),
    USER_NOT_LOGIN(HttpStatus.BAD_REQUEST, "로그아웃된 사용자 입니다.", 4),
    NOT_SAME_USER(HttpStatus.BAD_REQUEST, "토큰의 유저 정보가 일치하지 않습니다.", 5),
    NOT_FOUND_FRIEND(HttpStatus.BAD_REQUEST, "친구 정보를 찾을 수 없습니다.", 5),
    EXIST_ITEM(HttpStatus.BAD_REQUEST, "이미 존재하는 아이템 입니다.", 6),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다.", 7),
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
