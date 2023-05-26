package mars.ourmindmaze.dto.user;

import lombok.Data;

@Data
public class RequestUserSaveDto {
    private String email;
    private String password;
    private String name;
}