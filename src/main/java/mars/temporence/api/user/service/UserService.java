package mars.temporence.api.user.service;

import mars.temporence.api.user.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.user.event.dto.RequestUserLoginDto;
import mars.temporence.api.user.event.dto.RequestUserSaveDto;
import mars.temporence.api.user.event.dto.RequestTokenDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> save(RequestUserSaveDto dto);

    ResponseEntity<?> login(RequestUserLoginDto dto);

    ResponseEntity<?> findAll(Pageable pageable);

    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto);

    ResponseEntity<?> existNickname(RequestNicknameCheckDto dto);
}
