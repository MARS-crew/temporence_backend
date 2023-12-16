package mars.temporence.api.point.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.global.enums.PointType;

import javax.validation.constraints.NotNull;

@Data
public class RequestPointUpdateDto {
    @NotNull
    @Schema(example = "GOLD")
    private PointType pointType;

    @NotNull
    @Schema(example = "30")
    private Integer point;
}
