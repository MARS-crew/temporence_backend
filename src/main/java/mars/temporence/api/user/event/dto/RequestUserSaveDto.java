package mars.temporence.api.user.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestUserSaveDto {
    @Schema(example = "admin@test.com")
    private String username;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "자장면")
    private String nickname;
}