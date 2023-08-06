package mars.ourmindmaze.dto.playerLog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.ourmindmaze.enums.TeamType;

import java.util.List;

@Data
public class RequestLogSaveDto {

    @Schema(example = "RUNNER")
    private TeamType winner;

    @Schema(example = "700")
    private int playTime;

    private List<RequestPlayerSaveDto> players;

}
