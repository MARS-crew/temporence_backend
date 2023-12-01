package mars.temporence.global.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    METHOD_NOT_ALLOWED(405, "", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "", "Server Error");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
