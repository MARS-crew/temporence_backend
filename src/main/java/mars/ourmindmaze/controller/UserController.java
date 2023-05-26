package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import mars.ourmindmaze.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Save Usere", description = "유저 생성하기")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody RequestUserSaveDto dto) {
        return userService.save(dto);
    }

    @Operation(summary = "Login User", description = "유저 로그인")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestUserLoginDto dto) {
        return userService.login(dto);
    }

//    @Operation(summary = "Find User ALL", description = "유저 리스트 조회")
//    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
//    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
//    @ApiResponse(responseCode = "401", description = "Access denied")
//    @ApiResponse(responseCode = "500", description = "Internal Server Error")
//    @GetMapping
//    public ResponseEntity<?> findAll() {
//        return userService.findAll();
//    }

    @Operation(summary = "Get Token", description = "Access Token 재발급")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody RequestTokenDto dto) {
        return userService.getTokenByRefreshToken(dto);
    }
}
