package mars.temporence.api.dm.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.SwaggerConfig;
import mars.temporence.api.dm.event.dto.RequestDmSaveDto;
import mars.temporence.api.dm.service.DmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dm")
@RequiredArgsConstructor
@Tag(name = "DM API", description = "DM 관련 API")
public class DmController {

    private final DmService dmService;

    @Operation(summary = "Send Dm", description = "Dm 전송")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "dm 전송에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.DM_SEND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PostMapping()
    public ResponseEntity<?> sendDm(@RequestBody RequestDmSaveDto dto) {
        return dmService.sendDm(dto);
    }

    @Operation(summary = "Find Dm", description = "Dm 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "dm 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.DM_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerConfig.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerConfig.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerConfig.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerConfig.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerConfig.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping("/{id}")
    public ResponseEntity<?> findDm(@PathVariable(name = "id")Long id) {
        return dmService.findDm(id);
    }

}
