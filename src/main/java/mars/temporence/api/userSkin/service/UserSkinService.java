package mars.temporence.api.userSkin.service;

import mars.temporence.api.userSkin.event.dto.RequestUserSkinSaveDto;
import org.springframework.http.ResponseEntity;

public interface UserSkinService {
    ResponseEntity<?> saveUserSkin(RequestUserSkinSaveDto dto);
    ResponseEntity<?> findUserSkinList();
    ResponseEntity<?> deleteUserSkin(Long id);
}
