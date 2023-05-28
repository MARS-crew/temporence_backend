package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.dto.dm.RequestDmSaveDto;
import mars.ourmindmaze.service.DmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/dm")
@RequiredArgsConstructor
@Tag(name = "DM API", description = "메세지 API")
public class DmController {
    private final DmService dmService;

    @Operation(summary = "Send Message", description = "메세지 보내기")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @PostMapping
    public ResponseEntity<?> saveFriend(@RequestBody RequestDmSaveDto dto) {
        return dmService.saveDm(dto);
    }
}
