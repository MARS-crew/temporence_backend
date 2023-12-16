package mars.temporence.api.auth.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestUserSaveDto {
    @NotNull
    @Schema(example = "admin@test.com")
    private String username;

    @NotNull
    @Schema(example = "1234")
    private String password;

    @NotNull
    @Schema(example = "자장면")
    private String nickname;
}