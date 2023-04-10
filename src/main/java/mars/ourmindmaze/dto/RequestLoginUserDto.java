package mars.ourmindmaze.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class RequestLoginUserDto {
    private String email;
    private String password;
    public UsernamePasswordAuthenticationToken toAuthentication( ){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
