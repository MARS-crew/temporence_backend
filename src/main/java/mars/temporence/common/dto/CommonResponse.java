package mars.temporence.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CommonResponse {
    public static ResponseEntity<Object> createResponseMessage(final int statusCode, final String message) {
        return ApiResponse.builder().status(statusCode).message(message).data(null).buildObject();
    }

    public static ResponseEntity<Object> createResponseMessageAndCode(final int statusCode, final String message, final String code) {
        return ApiResponse.builder().status(statusCode).message(message).code(code).data(null).buildObject();
    }

    public static ResponseEntity<Object> createResponseMessageAndId(final int statusCode, final String message, final String id) {
        return ApiResponse.builder().status(statusCode).message(message).id(id).buildObject();
    }

    public static ResponseEntity<Object> createResponseMessageAndId(final int statusCode, final String message, final long id) {
        return ApiResponse.builder().status(statusCode).message(message).id(id).buildObject();
    }


    public static ResponseEntity<Object> createResponse(final int statusCode, final String message, Object data) {
        return ApiResponse.builder().status(statusCode).message(message).data(data).buildObject();
    }

    public static ResponseEntity<Object> createResponseAndId(final int statusCode, final String message, Object data, final String id) {
        return ApiResponse.builder().status(statusCode).message(message).data(data).id(id).buildObject();
    }

    public static ResponseEntity<Object> createResponseNotFound(final String ObjectName) {
        List<ErrorData> listErrorData = new ArrayList<>();
        ErrorData errorData = new ErrorData(ErrorMessage.API400006.getCode(), String.format(ErrorMessage.API400006.getMessage(), ObjectName));
        listErrorData.add(errorData);
        return ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value()).errors(listErrorData).buildObject();
    }

    public static ResponseEntity<Object> createResponseWithPagination(final int statusCode, final String message, Object data, Pagination pagination) {
        return ApiResponse.builder().status(statusCode).message(message).data(data).paging(pagination).buildObject();
    }


}
