package mars.temporence.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import mars.temporence.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * This is to define properties of the {@code ApiResponse}
 * </p>
 * Default value:
 * <ul>
 * <li>status: 200</li>
 * </ul>
 *
 * @param <T>
 *            the type of the output
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    /** Indicates that client cannot configure the [{@code data}] field */
    public static final String MSG_ERROR_DATA = "The [data] field is being assigned. It is not allowed because both [data] and [dataItems] cannot be assigned at the same time";

    /** Indicates that client cannot configure the [{@code dataItems}] field */
    public static final String MSG_ERROR_DATA_ITEMS = "The [dataItems] field field is being assigned. It is not allowed because both [data] and [dataItems] cannot be assigned at the same time";

    private int status;

    @JsonIgnore
    private T data;

    @JsonIgnore
    private List<T> dataItems;

    private String code;
    private String message;
    private T id;
    private String internalError;
    private List<ErrorData> errors;

    private Pagination pagination;

    /**
     * Writes the {@code ApiResponse<T>} to JSON. The {@code response} will be closed after data written
     *
     * @param response
     *            a {@link HttpServletResponse}
     * @throws IOException
     */
    public void writeJson(HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(this));
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * @param data
     *            the {@code data} to set
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> data(final T data) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.data = data;
        return builder;
    }

    /**
     * @param dataItems
     *            the {@code dataItems} to set
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> data(final List<T> dataItems) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.dataItems = dataItems;
        return builder;
    }

    /**
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> badRequest() {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        return builder.status(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * @param message
     *            the message should be written
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> badRequest(final String message) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.message = message;
        return builder.status(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * @param code
     *            the code should be written
     * @param message
     *            the message should be written
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> badRequest(final String code, final String message) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.code = code;
        builder.message = message;
        return builder.status(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * @param errors
     *            the list of {@code ErrorData} to set
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> badRequest(final List<ErrorData> errors) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        return builder.status(HttpStatus.BAD_REQUEST.value()).errors(errors);
    }

    /**
     * @param message
     *            the message should be written
     * @param errors
     *            the list of {@code ErrorData} to set
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> badRequest(final String message, final List<ErrorData> errors) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.message = message;
        return builder.status(HttpStatus.BAD_REQUEST.value()).errors(errors);
    }

    /**
     * @param code
     *            the code should be written
     * @param message
     *            the message should be written
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> internalServerError(final String code, final String message) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.code = code;
        builder.message = message;
        return builder.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Builds an {@code ApiResponseBuilder} based on {@code CustomiseException}
     *
     * @param ex
     *            a {@link CustomiseException}
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> customiseException(CustomiseException ex) {
        ApiResponseBuilder<T> builder = new ApiResponseBuilder<>();
        builder.status(ex.getStatusCode());
        builder.code = ex.getLocalizationCode();
        builder.message = ex.getMessage();
        return builder;
    }

    /**
     * @return {@code data} or {@code dataItems} of T
     */
    @JsonProperty("data")
    private Object getData() {
        if (data != null) {
            return data;
        }
        return dataItems;
    }

    /**
     * @param builder
     *            a {@link ApiResponseBuilder}
     */
    private ApiResponse(ApiResponseBuilder<T> builder) {
        this.status = builder.status;
        this.data = builder.data;
        this.dataItems = builder.dataItems;
        this.pagination = builder.paging;
        this.id = builder.id;

        this.code = builder.code;
        this.message = builder.message;
        this.internalError = builder.internalError;
        this.errors = builder.errors;
    }

    /**
     * @return an instance of {@code ApiResponseBuilder}
     */
    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    /**
     * @param <T>
     *            the type of the output
     */
    public static class ApiResponseBuilder<T> {

        private int status = HttpStatus.OK.value();
        private HttpStatus httpStatus = HttpStatus.OK;

        private T data;
        private List<T> dataItems;

        private String code;
        private String message;
        private T id;

        private String internalError;
        private List<ErrorData> errors;

        private Pagination paging;

        public ApiResponseBuilder<T> ApiResponseBuilder(ExceptionEnum exceptionEnum) {
            this.httpStatus = exceptionEnum.getStatus();
            this.code = String.valueOf(exceptionEnum.getErrorCode());
            this.message = exceptionEnum.getMessage();

            return this;
        }

        /**
         * @param status
         *            the status value to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> status(int status) {
            if (status > 99) {
                this.status = status;
                this.httpStatus = HttpStatus.valueOf(status);
            }
            return this;
        }

        /**
         * @param httpStatus
         *            the status value to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> status(HttpStatus httpStatus) {
            this.status = httpStatus.value();
            this.httpStatus = httpStatus;
            return this;
        }

        /**
         * @param data
         *            the {@code data} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> data(T data) {
            if (data != null && this.dataItems != null) {
                throw new IllegalArgumentException(MSG_ERROR_DATA);
            }
            this.data = data;
            return this;
        }

        /**
         * @param dataItems
         *            the list of {@code data} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> data(List<T> dataItems) {
            if (dataItems != null && this.data != null) {
                throw new IllegalArgumentException(MSG_ERROR_DATA_ITEMS);
            }
            this.dataItems = dataItems;
            return this;
        }

        /**
         * @param paging
         *            the {@link Pagination} to set
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> paging(Pagination paging) {
            this.paging = paging;
            return this;
        }

        /**
         * @param code
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        /**
         * @param message
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * @param id
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> id(T id) {
            this.id = id;
            return this;
        }

        /**
         * @param internalError
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> internalError(String internalError) {
            this.internalError = internalError;
            return this;
        }

        /**
         * @param errors
         * @return a {@code ApiResponseBuilder}
         */
        public ApiResponseBuilder<T> errors(List<ErrorData> errors) {
            this.errors = errors;
            return this;
        }

        /**
         * @return {@link ApiResponse}
         */
        public ApiResponse<T> init() {
            return new ApiResponse<>(this);
        }

        /**
         * Creates a {@code ResponseEntity} with the given {@code Object}
         *
         * @return the created {@code ResponseEntity}
         */
        public ResponseEntity<Object> buildObject() {
            return new ResponseEntity<>(init(), httpStatus);
        }

        /**
         * Creates a {@code ResponseEntity} with the given {@code Object}
         *
         * @return the created {@code ResponseEntity}
         */
        public ResponseEntity<ApiResponse<T>> buildEntity() {
            return new ResponseEntity<>(init(), httpStatus);
        }

        /**
         * @return a {@code ApiResponse}
         */
        public ApiResponse<T> build() {
            return new ApiResponse<>(this);
        }
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
