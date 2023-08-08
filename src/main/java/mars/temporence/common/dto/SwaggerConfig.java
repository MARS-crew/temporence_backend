package mars.temporence.common.dto;

public class SwaggerConfig {
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String UNAUTHORIZED_ERROR = "토큰이 유효하거나 존재하지 않습니다.";
    public static final String UNAUTHORIZED_ERROR_RESPONSE = "{\"status\":401,\"code\":\"API401001\",\"message\":\"unauthorized error full authentication is required to access this resource\"}";
    public static final String USER_FINDALL_RESPONSE =  "{\"status\":200,\"message\":\"유저 리스트를 조회 합니다.\",\"pagination\":{\"totalPages\":1,\"currentPage\":0,\"totalItems\":3},\"data\":[{\"id\":3,\"username\":\"admin2\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-06-04T20:35:02\"},{\"id\":2,\"username\":\"test\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-05-31T20:23:30\"},{\"id\":1,\"username\":\"admin\",\"socialType\":\"LOCAL\",\"createdDate\":\"2023-05-31T14:52:34\"}]}";
    public static final String POINT_UPDATE_RESPONSE = "{\"status\":200,\"message\":\"포인트를 수정합니다.\"}";
    public static final String POINT_FIND_RESPONSE =  "{\"status\":200,\"message\":\"포인트를 조회합니다.\",\"data\":{\"createdDate\":\"2023-05-31T14:52:34\",\"modifiedDate\":\"2023-05-31T14:52:34\",\"id\":1,\"gold\":30,\"blue\":0,\"user\":{\"createdDate\":\"2023-05-31T14:52:34\",\"modifiedDate\":\"2023-05-31T14:52:34\",\"id\":1,\"username\":\"admin\",\"password\":\"$2a$10$Wn2.uzB4eEyGW8Qh2p0uJ.ub5pngDJ6D/Q24YZl0slKCsGeAUf.iK\",\"socialType\":\"LOCAL\",\"socialKey\":null,\"authority\":\"ROLE_USER\"}}}";
    public static final String FRIEND_SAVE_RESPONSE = "{\"status\":200,\"message\":\"친구 등록에 성공하였습니다.\"}";
    public static final String FRIEND_UPDATE_TRUE_RESPONSE = "{\"status\":200,\"message\":\"친구 요청을 수락했습니다.\"}";
    public static final String FRIEND_UPDATE_FALSE_RESPONSE = "{\"status\":200,\"message\":\"친구 요청을 거절했습니다.\"}";
    public static final String FRIEND_FIND_RESPONSE = "{\"status\":200,\"message\":\"친구 리스트를 조회합니다.\",\"data\":[{\"id\":1,\"friendId\":2,\"friendName\":\"test\"}]}";
    public static final String FRIEND_REQUEST_FIND_RESPONSE = "{\"status\":200,\"message\":\"친구 요청 리스트를 조회합니다.\",\"data\":[{\"id\":1,\"friendId\":2,\"friendName\":\"test\"}]}";
    public static final String DM_SEND_RESPONSE = "{\"status\":201,\"message\":\"메세지를 보냈습니다.\"}";
    public static final String DM_FIND_RESPONSE = "{\"status\":200,\"message\":\"메세지 리스트를 출력합니다.\",\"data\":[{\"id\":1,\"sender\":\"admin\",\"receiver\":\"test\",\"content\":\"Hello\",\"createdDate\":\"2023-06-04T20:44:46\"}]}";
    public static final String DM_USER_FIND_RESPONSE = "{\"status\":200,\"message\":\"메세지 대상을 출력합니다.\",\"data\":[{\"id\":2,\"username\":\"test\"}]}";
    public static final String CHARACTER_SAVE_RESPONSE = "{\"status\":201,\"message\":\"캐릭터가 등록되었습니다.\"}";
    public static final String CHARACTER_DELETE_RESPONSE = "{\"status\":201,\"message\":\"캐릭터를 삭제하였습니다.\"}";
    public static final String CHARACTER_UPDATE_RESPONSE = "{\"status\":201,\"message\":\"캐릭터를 변경하였습니다.\"}";
    public static final String CHARACTER_FIND_RESPONSE = "{\"status\": 200, \"message\": \"캐릭터를 조회합니다.\", \"data\": {\"createdDate\": \"2023-06-08T00:01:11\", \"modifiedDate\": \"2023-06-08T00:01:11\", \"id\": 1, \"name\": \"뽀삐\"}}";
    public static final String CHARACTER_FINDLIST_RESPONSE = "{\"status\": 200, \"message\": \"캐릭터 리스트를 조회합니다.\", \"data\": [{\"createdDate\": \"2023-06-08T00:01:11\", \"modifiedDate\": \"2023-06-08T00:01:11\", \"id\": 1, \"name\": \"뽀삐\"}]}";
    public static final String SKIN_SAVE_RESPONSE = "{\"status\":201,\"message\":\"스킨이 등록되었습니다.\"}";
    public static final String SKIN_FIND_RESPONSE = "{\"status\":200,\"message\":\"스킨을 조회합니다.\",\"data\":{\"createdDate\":\"2023-06-08T00:34:02\",\"modifiedDate\":\"2023-06-08T00:34:02\",\"id\":4,\"name\":\"별 수호자 뽀삐\",\"userSkins\":[]}}";
    public static final String SKIN_FINDLIST_RESPONSE = "{\"status\":200,\"message\":\"스킨의 리스트를 조회합니다.\",\"data\":[{\"id\":4,\"name\":\"별 수호자 뽀삐\",\"createdDate\":\"2023-06-08T00:34:02\", \"teamType\":\"RUNNER\"}]}";
    public static final String SKIN_DELETE_RESPONSE = "{\"status\":201,\"message\":\"스킨을 삭제하였습니다.\"}";
    public static final String USER_SKIN_SAVE_RESPONSE = "{\"status\":201,\"message\":\"유저의 스킨이 등록되었습니다.\"}";
    public static final String USER_SKIN_FIND_RESPONSE = " \"{\\\"status\\\":200,\\\"message\\\":\\\"유저의 스킨 리스트를 조회합니다.\\\",\\\"data\\\":[{\\\"id\\\":1,\\\"skinId\\\":1,\\\"userId\\\":1,\\\"name\\\":\\\"별 수호자 뽀삐\\\",\\\"createdDate\\\":\\\"2023-06-08T09:52:03\\\",\\\"characterId\\\":1,\\\"characterName\\\":\\\"뽀삐\\\"}]}\"";
    public static final String USER_SKIN_DELETE_RESPONSE = "{\"status\":201,\"message\":\"유저의 스킨을 삭제하였습니다.\"}";
    public static final String FORBIDDEN_ERROR = "Forbidden";
    public static final String LOGIN_SUCCESS_RESPONSE = "{\"status\":201,\"message\":\"로그인에 성공하였습니다.\",\"data\":{\"id\":\"1\",\"accessToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2ODU5NjUwMzN9.XfwslGyww3Lx4wFRVwDsH5JVtx4e2WVWtN3F6X5vG56D1qtw7FhwMANiJ70PmJnBaoE8ObsiBmzNdCUILE0P8w\",\"email\":\"admin\",\"refreshToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2ODcwODgyMzN9.Ye8sXfvsHAZaoq-0c8vianij71Lop9yPeDzJJzvQbIa7AKctMP6gyQfk1Rz2gIbj4zBOa893877w1ZALhAERtg\"}}";
    public static final String REGISTER_SUCCESS_RESPONSE = "{\"status\":201,\"message\":\"회원가입에 성공하였습니다.\"}";
    public static final String EXIST_CHECK_NICKNAME = "{\"status\":200,\"message\":\"사용 가능한 닉네임 입니다.\"}";
    public static final String TOKEN_REFRESH_RESPONSE = "{\"status\":201,\"message\":\"토큰 재발급에 성공 하였습니다.\",\"data\":{\"grantType\":\"bearer\",\"accessToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2ODU5NjUxNzN9.XM5x6CCSVn0B4s2QBHRWuq6itXDlcr967m_-lMKZIcD1Rc2RY36k74VlyKUJGFyqdPF28kJvhq0wZ9QN2QUHlQ\",\"refreshToken\":\"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2ODcwODgzNzN9.IvsLCT5hQ1L4iIoZKyu15PAsMsMIaHquRc6bEqBp-WN1YXX03yJCCVp2kaKjbg0z0KbJnlHHeYWLe92FaAVpGA\",\"accessTokenExpiresIn\":1685965173647}}";

    public static final String LOG_SAVE_RESPONSE = "{\"status\":201,\"message\":\"로그 저장에 성공하였습니다.\",\"}";
    public static final String LOG_FIND_RESPONSE = " \"{\\\"status\\\":200,\\\"message\\\":\\\"유저의 플레이 기록를 조회합니다.\\\",\\\"data\\\":[{\\\"id\\\":1,\\\"logId\\\":1,\\\"userId\\\":1,\\\"characterId\\\":\\\"1\\\",\\\"role\\\":\\\"RUNNER\\\",\\\"prisonCount\\\":\\\"2\\\",\\\"createdDate\\\":\\\"2023-06-08T09:52:03\\\"}]}\"";

    public static final String BAD_REQUEST = "잘못된 요청 입니다.";
    public static final String NOT_FOUND = "찾을 수 없습니다.";
    public static final String BAD_REQUEST_RESPONSE = "{\"timestamp\":\"2023-06-02T06:39:44.234+00:00\",\"status\":400,\"error\":\"Bad Request\",\"path\":\"/api/auth/login\"}";
    public static final String INTERNAL_SERVER_ERROR_REPONSE = "{\"status\":500,\"errors\":[{\"status\":\"API500\",\"message\":\"Internal Server Error\"}]}";

}
