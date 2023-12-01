package mars.temporence.api.user.service;

import mars.temporence.api.user.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.user.event.dto.RequestUserLoginDto;
import mars.temporence.api.user.event.dto.RequestUserSaveDto;
import mars.temporence.api.user.event.dto.RequestTokenDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> save(RequestUserSaveDto dto) throws Exception;

    ResponseEntity<?> login(RequestUserLoginDto dto) throws Exception;

    ResponseEntity<?> findAll(Pageable pageable) throws Exception;

    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto) throws Exception;

    ResponseEntity<?> existNickname(RequestNicknameCheckDto dto) throws Exception;
}
