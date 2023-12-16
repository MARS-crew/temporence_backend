package mars.temporence.api.character.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.global.enums.TeamType;

import javax.validation.constraints.NotNull;

@Data
public class RequestCharacterSaveDto {
    @NotNull
    @Schema(example = "뽀삐")
    private String name;

    @NotNull
    @Schema(example = "RUNNER")
    private TeamType teamType;
}
