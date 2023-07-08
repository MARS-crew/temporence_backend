package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.common.dto.SwaggerConfig;
import mars.ourmindmaze.dto.user.RequestEmailCheckDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.service.UserService;
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
    private final UserService userService;

    @Operation(summary = "Save User", description = "유저 생성하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.REGISTER_SUCCESS_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody RequestUserSaveDto dto) {
        return userService.save(dto);
    }

    @Operation(summary = "Login User", description = "유저 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.LOGIN_SUCCESS_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestUserLoginDto dto) {
        return userService.login(dto);
    }

    @Operation(summary = "Get Token", description = "Access Token 재발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 재발급에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.TOKEN_REFRESH_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody RequestTokenDto dto) {
        return userService.getTokenByRefreshToken(dto);
    }

    @Operation(summary = "Exist Check Email", description = "이메일 중복 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이메일 중복 여부 확인합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.EXIST_CHECK_EMAIL))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"사용 중인 이메일 입니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/email")
    public ResponseEntity<?> existCheckEmail(@RequestBody RequestEmailCheckDto dto) {
        return userService.existEmailCheck(dto);
    }
}
