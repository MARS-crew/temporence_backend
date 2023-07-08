package mars.ourmindmaze.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestUserSaveDto {
    @Schema(example = "admin@test.co.")
    private String username;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "자장면")
    private String name;
}