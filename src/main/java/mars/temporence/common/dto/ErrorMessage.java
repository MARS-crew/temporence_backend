package mars.temporence.common.dto;

/**
 * Define internal code and custom message
 */
public enum ErrorMessage {

    //format code|message|field name
    //field name is optional
    API404001("API404", "Not Found", "Not Found"),
    API400001("API400001", "Bad Request. Check the parameter value.", ""),
    API400002("API400002", "Parameter type is incorrect", ""),
    API400003("API400003", "File too large", ""),
    API40011("API40011", "Body is empty", ""),
    API40009("API40009", "OTP code is empty", "code"),
    API40005("API40005", "Email is required", "email"),
    API40006("API40006", "Password is required", "password"),
    API40007("API40007", "refreshToken is required", "refreshToken"),
    API40008("API40008", "User isn't exist", ""),
    API40012("API40012", "this group was created by you so you can't leave that", ""),
    API400010("API400010", "Data is exists", ""),
    API400006("API400006", "%s isn't exists", ""),
    UNAUTHORIZED("API401001", "unauthorized error full authentication is required to access this resource", ""),
    API403002("API403002", "Access denied", ""),
    API403001("API403001", "Refresh token is not in database", ""),
    API415000("API415000", "MediaType not support", ""),
    API500001("API500", "Internal Server Error", "UNKNOW"),
    TOKEN_EXPIRED("TOKEN_EXPIRED", "Bad Credentials, JWT token expires ", ""),
    TOKEN_INVALID("TOKEN_INVALID", "Bad Credentials, JWT token is invalid ", ""),
    TOKEN_VALIDATE("401", "Bad Credentials, JWT token is VALIDATE ", "");


    private final String code;
    private final String message;
    private String fieldName;

    private ErrorMessage(String code, String message, String fieldName) {
        this.code = code;
        this.message = message;
        this.fieldName = fieldName;
    }

    /*
     * Find enum by code
     * return the enum base on code
     */
    public static ErrorMessage findByCode(String code) {
        for (ErrorMessage element : values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return API500001;//default Internal Server Error
    }

    /*
     * Find enum by field name
     * return the enum base on fieldName
     */
    public static ErrorMessage findByFieldName(String fieldName) {
        for (ErrorMessage element : values()) {
            if (element.getFieldName().equals(fieldName)) {
                return element;
            }
        }
        return API500001;//default Internal Server Error
    }

    public static ErrorMessage argumentNotValidFindByName(String fieldName) {
        for (ErrorMessage element : values()) {
            if (element.getFieldName().equals(fieldName)) {
                return element;
            }
        }
        return API400001;//default Internal Server Error
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }

}
