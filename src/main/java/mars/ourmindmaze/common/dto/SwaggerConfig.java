package mars.ourmindmaze.common.dto;

public class SwaggerConfig {
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String UNAUTHORIZED_ERROR = "토큰이 유효하거나 존재하지 않습니다.";
    public static final String UNAUTHORIZED_ERROR_RESPONSE = "{\"status\":401,\"code\":\"API401001\",\"message\":\"unauthorized error full authentication is required to access this resource\"}";
    public static final String USER_FINDALL_RESPONSE =  "{\"status\":200,\"message\":\"유저 리스트를 조회 합니다.\",\"pagination\":{\"totalPages\":1,\"currentPage\":0,\"totalItems\":3},\"data\":[{\"id\":3,\"username\":\"admin2\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-06-04T20:35:02\"},{\"id\":2,\"username\":\"test\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-05-31T20:23:30\"},{\"id\":1,\"username\":\"admin\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-05-31T14:52:34\"}]}";
    public static final String POINT_UPDATE_RESPONSE = "{\"status\":200,\"message\":\"포인트를 수정합니다.\"}";
    public static final String POINT_FIND_RESPONSE =  "{\"status\":200,\"message\":\"포인트를 조회합니다.\",\"data\":{\"createdDate\":\"2023-05-31T14:52:34\",\"modifiedDate\":\"2023-05-31T14:52:34\",\"id\":1,\"gold\":30,\"blue\":0,\"user\":{\"createdDate\":\"2023-05-31T14:52:34\",\"modifiedDate\":\"2023-05-31T14:52:34\",\"id\":1,\"username\":\"admin\",\"password\":\"$2a$10$Wn2.uzB4eEyGW8Qh2p0uJ.ub5pngDJ6D/Q24YZl0slKCsGeAUf.iK\",\"socialType\":\"LOCAL\",\"socialKey\":null,\"authority\":\"ROLE_USER\"}}}";
    public static final String NOTICE_FINDALL_RESPONSE = "{\"status\":200,\"message\":\"공지사항 리스트를 조회합니다.\",\"pagination\":{\"totalPages\":1,\"currentPage\":0,\"totalItems\":2},\"data\":[{\"id\":2,\"createdDate\":\"2023-06-02T16:07:02.778244\",\"title\":\"공지사항 1\",\"text\":\"공지사항 내용은 리스트에서 최대 2줄까지 노출 됩니다.\"},{\"id\":1,\"createdDate\":\"2023-06-01T10:15:18.514874\",\"title\":\"공지사항 1\",\"text\":\"공지사항 내용은 리스트에서 최대 2줄까지 노출 됩니다.\"}]}";
    public static final String NOTICE_FIND_RESPONSE = "{\"status\":200,\"message\":\"공지사항을 조회합니다.\",\"data\":{\"createdDate\":\"2023-06-01T10:15:18.514874\",\"modifiedDate\":\"2023-06-01T10:15:18.514874\",\"id\":1,\"title\":\"공지사항 1\",\"text\":\"공지사항 내용은 리스트에서 최대 2줄까지 노출 됩니다.\"}}";
    public static final String NOTICE_UPDATE_RESPONSE = "{\"status\":200,\"message\":\"공지사항을 변경하였습니다.\"}";
    public static final String NOTICE_DELETE_RESPONSE = "{\"status\":200,\"message\":\"공지사항을 삭제하였습니다.\"}";
    public static final String PICKUP_SAVE_SUCCESS_RESPONSE = "{\"status\":201,\"message\":\"픽업을 생성하였습니다\"}";
    public static final String PICKUP_DELETE_RESPONSE = "{\"status\":201,\"message\":\"픽업을 삭제하였습니다\"}";
    public static final String PICKUP_FINDALL_RESPONSE = "{\"status\":200,\"message\":\"픽업 리스트를 조회합니다.\",\"pagination\":{\"totalPages\":1,\"currentPage\":0,\"totalItems\":1},\"data\":[{\"id\":2,\"createdDate\":\"2023-06-02T16:15:37.712876\",\"store\":\"이마트 강남점\",\"date\":\"2023-05-25\",\"startTime\":\"2023-05-25 12:00:00\",\"endTime\":\"2023-05-25 14:00:00\",\"pickupStatus\":\"PICKUP\",\"username\":\"김인후\",\"cropsName\":\"사과나무\"}]}";
    public static final String PICKUP_FINDALL_USER_RESPONSE = "{\"status\":200,\"message\":\"유저의 픽업 리스트를 조회 합니다.\",\"pagination\":{\"totalPages\":1,\"currentPage\":0,\"totalItems\":1},\"data\":[{\"id\":2,\"createdDate\":\"2023-06-02T16:15:37.712876\",\"store\":\"이마트 강남점\",\"date\":\"2023-05-25\",\"startTime\":\"2023-05-25 12:00:00\",\"endTime\":\"2023-05-25 14:00:00\",\"pickupStatus\":\"PICKUP\",\"username\":\"김인후\",\"cropsName\":\"사과나무\"}]}";
    public static final String PICKUP_FIND_RESPONSE = "{\"status\":200,\"message\":\"픽업을 조회합니다.\",\"data\":{\"id\":2,\"createdDate\":\"2023-06-02T16:15:37.712876\",\"store\":\"이마트 강남점\",\"date\":\"2023-05-25\",\"startTime\":\"2023-05-25 12:00:00\",\"endTime\":\"2023-05-25 14:00:00\",\"pickupStatus\":\"PICKUP\",\"username\":\"김인후\",\"cropsName\":\"사과나무\"}}";
    public static final String USER_UPDATE_RESPONSE = "{\"status\":201,\"message\":\"닉네임을 변경하였습니다.\"}";
    public static final String DM_SEND_RESPONSE = "{\"status\":201,\"message\":\"메세지를 보냈습니다.\"}";
    public static final String DM_FIND_RESPONSE = "{\"status\":200,\"message\":\"메세지 리스트를 출력합니다.\",\"data\":[{\"id\":1,\"sender\":\"admin\",\"receiver\":\"test\",\"content\":\"Hello\",\"createdDate\":\"2023-06-04T20:44:46\"}]}";
    public static final String DM_USER_FIND_RESPONSE = "{\"status\":200,\"message\":\"메세지 대상을 출력합니다.\",\"data\":[{\"id\":2,\"username\":\"test\"}]}";
    public static final String USER_CROPS_UPDATE_RESPONSE = "{\"status\":201,\"message\":\"유저의 경험치를 증가시킵니다.\"}";
    public static final String FORBIDDEN_ERROR = "Forbidden";
    public static final String LOGIN_SUCCESS_RESPONSE = "{\"status\":201,\"message\":\"로그인에 성공하였습니다.\",\"data\":{\"id\":\"1\",\"accessToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2ODU5NjUwMzN9.XfwslGyww3Lx4wFRVwDsH5JVtx4e2WVWtN3F6X5vG56D1qtw7FhwMANiJ70PmJnBaoE8ObsiBmzNdCUILE0P8w\",\"email\":\"admin\",\"refreshToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2ODcwODgyMzN9.Ye8sXfvsHAZaoq-0c8vianij71Lop9yPeDzJJzvQbIa7AKctMP6gyQfk1Rz2gIbj4zBOa893877w1ZALhAERtg\"}}";
    public static final String REGISTER_SUCCESS_RESPONSE = "{\"status\":201,\"message\":\"회원가입에 성공하였습니다.\"}";
    public static final String TOKEN_REFRESH_RESPONSE = "{\"status\":201,\"message\":\"토큰 재발급에 성공 하였습니다.\",\"data\":{\"grantType\":\"bearer\",\"accessToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2ODU5NjUxNzN9.XM5x6CCSVn0B4s2QBHRWuq6itXDlcr967m_-lMKZIcD1Rc2RY36k74VlyKUJGFyqdPF28kJvhq0wZ9QN2QUHlQ\",\"refreshToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2ODcwODgzNzN9.IvsLCT5hQ1L4iIoZKyu15PAsMsMIaHquRc6bEqBp-WN1YXX03yJCCVp2kaKjbg0z0KbJnlHHeYWLe92FaAVpGA\",\"accessTokenExpiresIn\":1685965173647}}";
    public static final String BAD_REQUEST = "잘못된 요청 입니다.";
    public static final String BAD_REQUEST_RESPONSE = "{\"timestamp\":\"2023-06-02T06:39:44.234+00:00\",\"status\":400,\"error\":\"Bad Request\",\"path\":\"/api/auth/login\"}";
    public static final String INTERNAL_SERVER_ERROR_REPONSE = "{\"status\":500,\"errors\":[{\"status\":\"API500\",\"message\":\"Internal Server Error\"}]}";

}
