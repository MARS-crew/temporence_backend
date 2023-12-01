package mars.temporence.global.dto;

import lombok.Builder;
import lombok.Data;
import mars.temporence.global.enums.UserAuthority;

@Data
@Builder
public class UserDetailDto {
    private Long userId;
    private UserAuthority role;
}
