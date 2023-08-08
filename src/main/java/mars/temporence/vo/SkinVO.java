package mars.temporence.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mars.temporence.enums.TeamType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SkinVO {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private TeamType teamType;
}
