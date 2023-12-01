package mars.temporence.api.userSkin.ui;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.userSkin.event.dto.RequestUserSkinSaveDto;
import mars.temporence.api.userSkin.service.UserSkinService;
import mars.temporence.global.dto.SwaggerExampleValue;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.jwt.JwtTokenExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user/skin")
@RequiredArgsConstructor
@Tag(name = "User Skin API", description = "유저의 캐릭터 스킨 관련 API")
public class UserSkinController {
    private final UserSkinService userSkinService;
    private final JwtTokenExtractor jwtTokenExtractor;


    @Operation(summary = "Save User Skin", description = "유저의 캐릭터 스킨 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저의 캐릭터 스킨 생성에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_SKIN_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveUserSkin(@RequestBody RequestUserSkinSaveDto dto, HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);

        return userSkinService.saveUserSkin(dto, userDetail);
    }

    @Operation(summary = "Find User Skin List", description = "유저의 캐릭터 스킨 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저의 캐릭터 스킨 리스트 조회에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_SKIN_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping
    public ResponseEntity<?> findUserSkinList(HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);

        return userSkinService.findUserSkinList(userDetail);
    }

    @Operation(summary = "Delete User Skin", description = "유저의 캐릭터 스킨 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저의 캐릭터 스킨 삭제에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_SKIN_DELETE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserSkin(@PathVariable(name = "id") Long id) throws Exception {
        return userSkinService.deleteUserSkin(id);
    }
}
