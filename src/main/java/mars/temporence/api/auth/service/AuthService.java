package mars.temporence.api.auth.service;

import mars.temporence.api.user.event.dto.RequestTokenDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto, UserDetailDto userDetailDto) throws Exception;
}
