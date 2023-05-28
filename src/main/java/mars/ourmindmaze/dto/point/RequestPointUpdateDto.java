package mars.ourmindmaze.dto.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.ourmindmaze.enums.PointType;

@Data
public class RequestPointUpdateDto {
    @Schema(example = "GOLD")
    private PointType pointType;

    @Schema(example = "30")
    private Integer point;
}
