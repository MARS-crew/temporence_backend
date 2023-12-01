package mars.temporence.api.friend.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.friend.event.dto.RequestFriendSaveDto;
import mars.temporence.api.friend.event.dto.RequestFriendUpdateDto;
import mars.temporence.api.friend.service.FriendService;
import mars.temporence.global.dto.SwaggerExampleValue;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.jwt.JwtTokenExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
@Tag(name = "Friend API", description = "친구 관련 API")
public class FriendController {
    private final FriendService friendService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "Save Friend", description = "친구 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 생성에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FRIEND_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400-1", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"\"이미 친구 입니다.\"}")})),
            @ApiResponse(responseCode = "400-2", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"본인은 친구를 추가할 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "400-3", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":400,\"message\":\"이미 친구 요청을 보낸 대상 입니다.\"}")})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":404,\"message\":\"친구 할 유저를 찾을 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveFriend(@RequestBody RequestFriendSaveDto dto, HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);
        return friendService.saveFriend(dto, userDetail);
    }

    @Operation(summary = "Find Friend List", description = "친구 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FRIEND_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping
    public ResponseEntity<?> findFriendList(HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);
        return friendService.findFriendList(userDetail);
    }

    @Operation(summary = "Delete Friend", description = "친구 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 삭제에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FRIEND_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> findFriendList(@PathVariable(name = "id") Long id) throws Exception {
        return friendService.deleteFriend(id);
    }

    @Operation(summary = "Find Friend List", description = "친구 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 요청 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FRIEND_REQUEST_FIND_RESPONSE))),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/request")
    public ResponseEntity<?> findFriendRequestList(HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);
        return friendService.findFriendRequestList(userDetail);
    }

    @Operation(summary = "Response about Friend Request", description = "친구 요청에 대한 응답")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 요청에 대한 응답을 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FRIEND_UPDATE_TRUE_RESPONSE))),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = "{\"status\":404,\"message\":\"친구를 찾을 수 없습니다.\"}")})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping("/response")
    public ResponseEntity<?> updateFriend(@RequestBody RequestFriendUpdateDto dto) throws Exception {
        return friendService.updateFriend(dto);
    }
}
