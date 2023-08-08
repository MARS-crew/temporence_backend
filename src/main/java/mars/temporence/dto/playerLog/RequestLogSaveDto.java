package mars.temporence.dto.playerLog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.temporence.enums.TeamType;

import java.util.List;

@Data
public class RequestLogSaveDto {

    @Schema(example = "RUNNER")
    private TeamType winner;

    @Schema(example = "700")
    private int playTime;

    private List<RequestPlayerSaveDto> players;

}
