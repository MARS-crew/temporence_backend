package mars.temporence.api.userSkin.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSkinVO {
    private Long id;
    private Long skinId;
    private Long userId;
    private String name;
    private LocalDateTime createdDate;
    private Long characterId;
    private String characterName;
}
