package mars.temporence.dto.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.enums.TeamType;

@Data
public class RequestCharacterSaveDto {
    @Schema(example = "뽀삐")
    private String name;

    @Schema(example = "RUNNER")
    private TeamType teamType;
}
