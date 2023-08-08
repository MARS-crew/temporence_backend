package mars.temporence.dto.user;

import lombok.Data;

@Data
public class RequestTokenDto {
    private String refreshToken;
    private String accessToken;
}
