package mars.ourmindmaze.common.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestResponseDto<D> {
    private Integer status;

    private Code code;

    private String message;

    private D responseData;

    public enum Code {
        SUCCESS,
        FAILED
    }

    public static <D> RequestResponseDto<D> of (HttpStatus status, Code code, String message, D responseData) {
        RequestResponseDto<D> response = new RequestResponseDto<>();
        response.setStatus(status.value());
        response.setCode(code);
        response.setMessage(message);
        response.setResponseData(responseData);
        return response;
    }
}
