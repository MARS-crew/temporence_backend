package mars.ourmindmaze.dto.dm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestDmSaveDto {
    @Schema(example = "6")
    private Long reciverId;

    @Schema(example = "Hello")
    private String content;
}
