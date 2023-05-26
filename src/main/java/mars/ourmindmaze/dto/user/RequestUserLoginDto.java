package mars.ourmindmaze.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class RequestUserLoginDto {
    @Schema(example = "admimn")
    private String email;

    @Schema(example = "1234")
    private String password;
    public UsernamePasswordAuthenticationToken toAuthentication( ){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
