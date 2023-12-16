package mars.temporence.api.auth.service;

import mars.temporence.api.user.event.dto.RequestNicknameCheckDto;
import mars.temporence.api.user.event.dto.RequestTokenDto;
import mars.temporence.api.user.event.dto.RequestUserLoginDto;
import mars.temporence.api.user.event.dto.RequestUserSaveDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> getTokenByRefreshToken(RequestTokenDto dto) throws Exception;

    ResponseEntity<?> save(RequestUserSaveDto dto) throws Exception;

    ResponseEntity<?> login(RequestUserLoginDto dto) throws Exception;

    ResponseEntity<?> existNickname(RequestNicknameCheckDto dto) throws Exception;

}
