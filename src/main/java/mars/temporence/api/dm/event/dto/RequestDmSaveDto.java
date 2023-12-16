package mars.temporence.api.dm.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestDmSaveDto {
    @NotNull
    @Schema(example = "1")
    private Long reciverId;

    @NotNull
    @Schema(example = "Hello")
    private String content;

}
