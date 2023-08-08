package mars.temporence.service;

import mars.temporence.dto.user.RequestNicknameCheckDto;
import mars.temporence.dto.user.RequestUserLoginDto;
import mars.temporence.dto.user.RequestUserSaveDto;
import mars.temporence.dto.user.RequestTokenDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> save(RequestUserSaveDto dto);

    ResponseEntity<?> login(RequestUserLoginDto dto);

    ResponseEntity<?> findAll(Pageable pageable);

    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto);

    ResponseEntity<?> existNickname(RequestNicknameCheckDto dto);
}
