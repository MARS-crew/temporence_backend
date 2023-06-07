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
import mars.ourmindmaze.dto.character.RequestCharacterSaveDto;
import mars.ourmindmaze.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
@Tag(name = "Character API", description = "캐릭터 관련 API")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Save Chacter", description = "캐릭터 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 생성에 성공하였습니다..", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.CHARACTER_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveCharacter(@RequestBody RequestCharacterSaveDto dto) {
        return characterService.saveCharacter(dto);
    }
}
