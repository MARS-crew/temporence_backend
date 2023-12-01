package mars.temporence.api.point.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.point.event.dto.RequestPointUpdateDto;
import mars.temporence.api.point.service.PointService;
import mars.temporence.global.dto.SwaggerExampleValue;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.jwt.JwtTokenExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
@Tag(name = "Point API", description = "포인트 API")
public class PointController {
    private final PointService pointService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "Find My Point", description = "포인트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포인트 조회에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.POINT_FIND_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @GetMapping
    public ResponseEntity<?> findMyPoint(HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);

        return pointService.findMyPoint(userDetail);
    }

    @Operation(summary = "Update My Point", description = "포인트 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "포인트 수정에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.POINT_UPDATE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = SwaggerExampleValue.BAD_REQUEST, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.BAD_REQUEST_RESPONSE)})),
            @ApiResponse(responseCode = "401", description = SwaggerExampleValue.UNAUTHORIZED_ERROR, content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UNAUTHORIZED_ERROR_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = SwaggerExampleValue.INTERNAL_SERVER_ERROR, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_REPONSE)))})
    @PutMapping
    public ResponseEntity<?> updateMyPount(@RequestBody RequestPointUpdateDto dto, HttpServletRequest request) throws Exception {
        UserDetailDto userDetail = jwtTokenExtractor.extractUserId(request);

        return pointService.updatePoint(dto, userDetail);
    }
}
