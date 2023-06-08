package mars.ourmindmaze.dto.userSkin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestUserSkinSaveDto {

    @Schema(example = "1")
    private Long skinId;
}
