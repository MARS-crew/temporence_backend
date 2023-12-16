package mars.temporence.api.auth.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestNicknameCheckDto {
    @Schema(example = "자장면")
    private String nickname;
}
