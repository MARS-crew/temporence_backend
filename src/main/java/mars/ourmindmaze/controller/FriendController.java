package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.dto.friend.RequestFriendSaveDto;
import mars.ourmindmaze.service.FriendService;
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
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PutMapping
    public ResponseEntity<?> saveFriend(@RequestBody RequestFriendSaveDto dto) {
        return friendService.saveFriend(dto);
    }

    @Operation(summary = "Find Friend List", description = "친구 리스트 조회")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping
    public ResponseEntity<?> findFriendList() {
        return friendService.findFriendList();
    }

    @Operation(summary = "Delete Friend", description = "친구 삭제")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> findFriendList(@PathVariable(name = "id") Long id) {
        return friendService.deleteFriend(id);
    }
}
