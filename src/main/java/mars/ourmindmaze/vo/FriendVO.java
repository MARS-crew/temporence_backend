package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendVO {
    private Long id;
    private Long friendId;
    private String friendName;
}
