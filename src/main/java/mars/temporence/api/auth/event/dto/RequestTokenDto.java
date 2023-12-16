package mars.temporence.api.auth.event.dto;

import lombok.Data;

@Data
public class RequestTokenDto {
    private String refreshToken;
}
