package mars.ourmindmaze.dto;

import lombok.Data;

@Data
public class RequestTokenDto {
    private String refreshToken;
    private String accessToken;
}
