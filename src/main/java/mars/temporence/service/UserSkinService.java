package mars.temporence.service;

import mars.temporence.dto.userSkin.RequestUserSkinSaveDto;
import org.springframework.http.ResponseEntity;

public interface UserSkinService {
    ResponseEntity<?> saveUserSkin(RequestUserSkinSaveDto dto);
    ResponseEntity<?> findUserSkinList();
    ResponseEntity<?> deleteUserSkin(Long id);
}
