package mars.temporence.api.friend.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestFriendSaveDto {
    @NotNull
    @Schema(example = "자장면")
    private String nickname;
}
