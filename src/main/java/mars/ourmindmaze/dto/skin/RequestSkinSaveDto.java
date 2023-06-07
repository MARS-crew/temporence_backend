package mars.ourmindmaze.dto.skin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestSkinSaveDto {
    @Schema(example = "별 수호자 뽀삐")
    private String name;

    @Schema(example = "1")
    private Long characterId;
}
