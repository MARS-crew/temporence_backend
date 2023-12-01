package mars.temporence.api.skin.service;

import mars.temporence.api.skin.event.dto.RequestSkinSaveDto;
import mars.temporence.global.enums.TeamType;
import org.springframework.http.ResponseEntity;

public interface SkinService {
    ResponseEntity<?> saveSkin(RequestSkinSaveDto dto);
    ResponseEntity<?> findSkinById(Long id);
    ResponseEntity<?> deleteSkin(Long id);
    ResponseEntity<?> findSkinList();
    ResponseEntity<?> findSkinListByTeamType(TeamType teamType);
}
