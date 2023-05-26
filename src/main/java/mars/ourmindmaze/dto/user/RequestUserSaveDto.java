package mars.ourmindmaze.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestUserSaveDto {
    @Schema(example = "admin@admin.com")
    private String email;

    @Schema(example = "1234")
    private String password;

    @Schema(example = "사용자")
    private String name;
}