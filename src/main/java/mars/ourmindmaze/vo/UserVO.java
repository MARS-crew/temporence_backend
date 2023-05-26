package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.enums.UserType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String email;
    private String name;
    private UserType userType;
    private LocalDate createdDate;
}
