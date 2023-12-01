package mars.temporence.api.skin.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mars.temporence.global.enums.TeamType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SkinVO {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private TeamType teamType;
}
