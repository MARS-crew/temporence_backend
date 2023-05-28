package mars.ourmindmaze.dto.friend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestFriendSaveDto {
    @Schema(example = "1")
    private Long friendId;
}
