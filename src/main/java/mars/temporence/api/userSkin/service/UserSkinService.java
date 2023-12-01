package mars.temporence.api.userSkin.service;

import mars.temporence.api.userSkin.event.dto.RequestUserSkinSaveDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface UserSkinService {
    ResponseEntity<?> saveUserSkin(RequestUserSkinSaveDto dto, UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> findUserSkinList(UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> deleteUserSkin(Long id) throws Exception;
}
