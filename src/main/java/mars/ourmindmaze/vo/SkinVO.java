package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mars.ourmindmaze.enums.TeamType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SkinVO {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private TeamType teamType;
}
