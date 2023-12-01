package mars.temporence.api.skin.ui;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.skin.event.dto.RequestSkinSaveDto;
import mars.temporence.global.dto.SwaggerExampleValue;
import mars.temporence.global.enums.TeamType;
import mars.temporence.api.skin.service.SkinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/skin")
@RequiredArgsConstructor
@Tag(name = "Skin API", description = "캐릭터 스킨 관련 API")
public class SkinController {
    private final SkinService skinService;

    @Operation(summary = "Save Skin", description = "캐릭터 스킨 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 스킨 생성에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SKIN_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveSkin(@RequestBody RequestSkinSaveDto dto) throws Exception {
        return skinService.saveSkin(dto);
    }

    @Operation(summary = "Find Skin", description = "캐릭터 스킨 조회 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 스킨 조회에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SKIN_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/{id}")
    public ResponseEntity<?> findSkinById(@PathVariable(name = "id") Long id)throws Exception {
        return skinService.findSkinById(id);
    }

    @Operation(summary = "Find Skin List", description = "캐릭터 스킨 리스트 조회 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 스킨 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SKIN_FINDLIST_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping
    public ResponseEntity<?> findSkinList()throws Exception {
        return skinService.findSkinList();
    }

    @Operation(summary = "Find Skin List By TeamType", description = "특정 팀 스킨 리스트 조회 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 팀 팀 스킨 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SKIN_FINDLIST_RESPONSE))),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/team")
    public ResponseEntity<?> findSkinListByCharacter(@RequestParam(name = "status", required = true) String status)throws Exception {
        if (status.equals("RUNNER")) {
            return skinService.findSkinListByTeamType(TeamType.RUNNER);
        }
        return skinService.findSkinListByTeamType(TeamType.STOPPER);
    }

    @Operation(summary = "Delete Skin", description = "캐릭터 스킨 조회 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 스킨 삭제에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SKIN_DELETE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkin(@PathVariable(name = "id") Long id)throws Exception {
        return skinService.deleteSkin(id);
    }
}
