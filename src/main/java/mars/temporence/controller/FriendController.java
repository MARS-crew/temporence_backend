package mars.temporence.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.common.dto.SwaggerConfig;
import mars.temporence.dto.friend.RequestFriendSaveDto;
import mars.temporence.dto.friend.RequestFriendUpdateDto;
import mars.temporence.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
@Tag(name = "Friend API", description = "친구 관련 API")
public class FriendController {
    private final FriendService friendService;

    @Operation(summary = "Save Friend", description = "친구 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 생성에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"\"이미 친구 입니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"본인은 친구를 추가할 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"이미 친구 요청을 보낸 대상 입니다.\"}")})),
            @ApiResponse(responseCode = "404", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":404,\"message\":\"친구 할 유저를 찾을 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveFriend(@RequestBody RequestFriendSaveDto dto) {
        return friendService.saveFriend(dto);
    }

    @Operation(summary = "Find Friend List", description = "친구 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping
    public ResponseEntity<?> findFriendList() {
        return friendService.findFriendList();
    }

    @Operation(summary = "Delete Friend", description = "친구 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 삭제에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> findFriendList(@PathVariable(name = "id") Long id) {
        return friendService.deleteFriend(id);
    }

    @Operation(summary = "Find Friend List", description = "친구 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 요청 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_REQUEST_FIND_RESPONSE))),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/request")
    public ResponseEntity<?> findFriendRequestList() {
        return friendService.findFriendRequestList();
    }

    @Operation(summary = "Response about Friend Request", description = "친구 요청에 대한 응답")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 요청에 대한 응답을 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_UPDATE_TRUE_RESPONSE))),
            @ApiResponse(responseCode = "201", description = "친구 요청에 대한 응답을 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.FRIEND_UPDATE_FALSE_RESPONSE))),
            @ApiResponse(responseCode = "404", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":404,\"message\":\"친구를 찾을 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/response")
    public ResponseEntity<?> updateFriend(@RequestBody RequestFriendUpdateDto dto) {
        return friendService.updateFriend(dto);
    }
}
