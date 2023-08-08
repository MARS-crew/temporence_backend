package mars.temporence.dto.skin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.enums.TeamType;

@Data
public class RequestSkinSaveDto {
    @Schema(example = "별 수호자 뽀삐")
    private String name;

    @Schema(example = "RUNNER")
    private TeamType teamType;
}
