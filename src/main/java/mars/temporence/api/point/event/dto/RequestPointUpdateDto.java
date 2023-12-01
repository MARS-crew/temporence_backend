package mars.temporence.api.point.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.global.enums.PointType;

@Data
public class RequestPointUpdateDto {
    @Schema(example = "GOLD")
    private PointType pointType;

    @Schema(example = "30")
    private Integer point;
}
