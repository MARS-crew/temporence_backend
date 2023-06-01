package mars.ourmindmaze.common.dto;

public class SwaggerConfig {
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String UNAUTHORIZED_ERROR = "Unauthorized";
    public static final String FORBIDDEN_ERROR = "Forbidden";
    public static final String LOGIN_SUCCESS ="{\"expiration\":\"300\",\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBlbWFpbC5jb20iLCJpYXQiOjE2NjA3OTI3MDUsImV4cCI6MTY2MDc5MzAwNX0.q11oRYEmNIsXoG9TLbbSyfY5dFeQe5rFWiIBkiXpLbGhyZcGs-fzWcpCf1bQZSMEq64IXkO14Mu0M7UAMGjJig\",\"refreshToken\":\"01cd735f-3146-4053-aada-a9685e2072af\"}";
    public static final String SAVE_SUCCESS ="회원가입 성공";
    public static final String INTERNAL_SERVER_ERROR_REPONSE ="{\"status\":500,\"errors\":[{\"status\":\"API500\",\"message\":\"Internal Server Error\"}]}";
    public static final String TOKEN_EXPIRED_REPONSE = "{\"status\":401,\"code\":\"TOKEN_EXPIRED\",\"message\":\"Bad Credentials, JWT token expires \"}";
    public static final String UNAUTHORIZED_ACCESS_DENIED_RESPONSE = "{\"status\":403,\"errors\":[{\"status\":\"API403002\",\"message\":\"Access denied\"}]}";

}
