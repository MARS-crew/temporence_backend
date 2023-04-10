package mars.ourmindmaze.dto;

import lombok.Data;

@Data
public class RequestSaveUserDto {
    private String email;
    private String password;
    private String mbti;
    private String name;
}