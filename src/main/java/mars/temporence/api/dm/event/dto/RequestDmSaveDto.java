package mars.temporence.api.dm.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestDmSaveDto {
    @Schema(example = "1")
    private Long reciverId;

    @Schema(example = "Hello")
    private String content;

}
