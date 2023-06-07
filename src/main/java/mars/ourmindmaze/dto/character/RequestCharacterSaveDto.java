package mars.ourmindmaze.dto.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestCharacterSaveDto {
    @Schema(example = "뽀삐")
    private String name;
}
