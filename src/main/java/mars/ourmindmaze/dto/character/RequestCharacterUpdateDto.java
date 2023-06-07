package mars.ourmindmaze.dto.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestCharacterUpdateDto {
    @Schema(example = "뽀오삐")
    private String name;
}
