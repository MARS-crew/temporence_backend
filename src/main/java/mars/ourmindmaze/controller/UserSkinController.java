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
import mars.ourmindmaze.dto.userSkin.RequestUserSkinSaveDto;
import mars.ourmindmaze.service.UserSkinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user/skin")
@RequiredArgsConstructor
@Tag(name = "User Skin API", description = "유저의 캐릭터 스킨 관련 API")
public class UserSkinController {
    private final UserSkinService userSkinService;

    @Operation(summary = "Save User Skin", description = "유저의 캐릭터 스킨 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저의 캐릭터 스킨 생성에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.USER_SKIN_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveUserSkin(@RequestBody RequestUserSkinSaveDto dto) {
        return userSkinService.saveUserSkin(dto);
    }
}
