package mars.temporence.api.user.event.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private LocalDateTime createdDate;
}
