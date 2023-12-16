package mars.temporence.api.friend.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestFriendUpdateDto {
    @NotNull
    @Schema(example = "5")
    private Long friendId;

    @NotNull
    @Schema(example = "Y")
    private String status;
}
