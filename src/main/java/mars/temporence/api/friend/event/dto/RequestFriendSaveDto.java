package mars.temporence.api.friend.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestFriendSaveDto {
    @Schema(example = "자장면")
    private String nickname;
}
