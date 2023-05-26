package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> save(RequestUserSaveDto dto);

    ResponseEntity<?> login(RequestUserLoginDto dto);

//    ResponseEntity<?> findAll();

    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto);
}
