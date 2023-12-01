package mars.temporence.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private T data;
    private String message;
    private Pagination pagination;

    public static <T> ApiResponseBuilder<T> data(final T data) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.data = data;
        return builder;
    }

    private ApiResponse(ApiResponseBuilder<T> builder) {
        this.code = builder.code;
        this.status = builder.httpStatus;
        this.data = builder.data;
        this.pagination = builder.paging;
        this.message = builder.message;
    }

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public static class ApiResponseBuilder<T> {
        private int code = HttpStatus.OK.value();
        private HttpStatus httpStatus = HttpStatus.OK;
        private T data;
        private String message;

        private Pagination paging;

        public ApiResponseBuilder<T> status(int status) {
            if (status > 99) {
                this.code = status;
                this.httpStatus = HttpStatus.valueOf(status);
            }
            return this;
        }

        public ApiResponseBuilder<T> pagination(Pagination paging) {
            this.paging = paging;
            return this;
        }

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponse<T> init() {
            return new ApiResponse<>(this);
        }

        public ResponseEntity<Object> buildObject() {
            return new ResponseEntity<>(init(), httpStatus);
        }
    }
}
