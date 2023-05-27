package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mars.ourmindmaze.enums.SocialType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private SocialType socialType;
    private LocalDate createdDate;
}
