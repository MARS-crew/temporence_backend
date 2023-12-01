package mars.temporence.api.userSkin.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestUserSkinSaveDto {

    @Schema(example = "1")
    private Long skinId;
}
