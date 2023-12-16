package mars.temporence.api.auth.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.auth.service.AuthService;
import mars.temporence.api.auth.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.auth.event.dto.RequestTokenDto;
import mars.temporence.api.auth.event.dto.RequestUserLoginDto;
import mars.temporence.api.auth.event.dto.RequestUserSaveDto;
import mars.temporence.global.dto.SwaggerExampleValue;
import mars.temporence.global.jwt.JwtTokenExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "인증 API")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "Save User", description = "유저 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.REGISTER_SUCCESS_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"사용 중인 이메일 입니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"사용 중인 닉네임 입니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody RequestUserSaveDto dto) throws Exception {
        return authService.save(dto);
    }

    @Operation(summary = "Login User", description = "유저 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.LOGIN_SUCCESS_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"패스워드가 일치하지 않습니다.\"}")})),
            @ApiResponse(responseCode = "404", description = SwaggerExampleValue.NOT_FOUND, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":404,\"message\":\"유저를 찾을 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestUserLoginDto dto) throws Exception {
        return authService.login(dto);
    }

    @Operation(summary = "Get Token", description = "Access Token 재발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 재발급에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.TOKEN_REFRESH_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"토큰이 유효하지 않습니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"유저가 로그인 상태가 아닙니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"유저 정보가 일치하지 않습니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"토큰이 만료되었습니다.\"}")})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody RequestTokenDto dto) throws Exception {
        return authService.getTokenByRefreshToken(dto);
    }

    @Operation(summary = "Exist Check Nickname", description = "닉네임 중복 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "닉네임 중복 여부 확인합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.EXIST_CHECK_NICKNAME))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"사용 중인 닉네임 입니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/nickname")
    public ResponseEntity<?> existCheckEmail(@RequestBody RequestNicknameCheckDto dto) throws Exception {
        return authService.existNickname(dto);
    }
}
