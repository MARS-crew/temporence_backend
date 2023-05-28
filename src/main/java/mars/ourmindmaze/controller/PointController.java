package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.ourmindmaze.service.PointService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
@Tag(name = "Point API", description = "포인트 API")
public class PointController {
    private final PointService pointService;
}
