package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mars.ourmindmaze.enums.SocialType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private SocialType socialType;
    private LocalDateTime createdDate;
}
