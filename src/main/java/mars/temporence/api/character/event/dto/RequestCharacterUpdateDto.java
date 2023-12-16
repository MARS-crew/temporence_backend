package mars.temporence.api.character.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestCharacterUpdateDto {
    @NotNull
    @Schema(example = "뽀오삐")
    private String name;
}
