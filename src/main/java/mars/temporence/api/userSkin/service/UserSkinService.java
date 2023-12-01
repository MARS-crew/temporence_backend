package mars.temporence.api.userSkin.service;

import mars.temporence.api.userSkin.event.dto.RequestUserSkinSaveDto;
import org.springframework.http.ResponseEntity;

public interface UserSkinService {
    ResponseEntity<?> saveUserSkin(RequestUserSkinSaveDto dto) throws Exception;
    ResponseEntity<?> findUserSkinList() throws Exception;
    ResponseEntity<?> deleteUserSkin(Long id) throws Exception;
}
