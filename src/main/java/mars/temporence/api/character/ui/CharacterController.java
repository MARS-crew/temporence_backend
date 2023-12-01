package mars.temporence.api.character.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.character.event.dto.RequestCharacterSaveDto;
import mars.temporence.api.character.event.dto.RequestCharacterUpdateDto;
import mars.temporence.api.character.service.CharacterService;
import mars.temporence.global.dto.SwaggerExampleValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
@Tag(name = "Character API", description = "캐릭터 관련 API")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Save Chacter", description = "캐릭터 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 생성에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.CHARACTER_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveCharacter(@RequestBody RequestCharacterSaveDto dto) throws Exception {
        return characterService.saveCharacter(dto);
    }

    @Operation(summary = "Find Chacter", description = "캐릭터 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.CHARACTER_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/{id}")
    public ResponseEntity<?> findCharacterById(@PathVariable(name = "id") Long id) throws Exception {
        return characterService.findCharacterById(id);
    }

    @Operation(summary = "Find Chacter List", description = "캐릭터 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 리스트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.CHARACTER_FINDLIST_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping()
    public ResponseEntity<?> findCharacter() throws Exception {
        return characterService.findCharacter();
    }

    @Operation(summary = "Update Chacter", description = "캐릭터 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 수정에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.CHARACTER_UPDATE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCharacter(@RequestBody RequestCharacterUpdateDto dto, @PathVariable(name = "id") Long id) throws Exception {
        return characterService.updateCharacter(dto, id);
    }

    @Operation(summary = "Delete Chacter", description = "캐릭터 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 삭제에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.CHARACTER_DELETE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable(name = "id") Long id) throws Exception {
        return characterService.deleteCharacter(id);
    }
}
