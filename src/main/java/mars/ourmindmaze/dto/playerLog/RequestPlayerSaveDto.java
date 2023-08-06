package mars.ourmindmaze.dto.playerLog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import mars.ourmindmaze.enums.TeamType;
@Data
public class RequestPlayerSaveDto {

    @Schema(example = "noob")
    private String Nickname;

    @Schema(example = "뽀삐")
    private String characterNickname;

    @Schema(example = "RUNNER")
    private TeamType role;

    @Schema(example = "1") 
    private Integer prisonCount;

}
