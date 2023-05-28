package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mars.ourmindmaze.enums.SocialType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FriendVO {
    private Long id;
    private Long friendId;
    private String friendName;
}
