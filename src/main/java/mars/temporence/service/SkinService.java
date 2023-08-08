package mars.temporence.service;

import mars.temporence.dto.skin.RequestSkinSaveDto;
import mars.temporence.enums.TeamType;
import org.springframework.http.ResponseEntity;

public interface SkinService {
    ResponseEntity<?> saveSkin(RequestSkinSaveDto dto);
    ResponseEntity<?> findSkinById(Long id);
    ResponseEntity<?> deleteSkin(Long id);
    ResponseEntity<?> findSkinList();
    ResponseEntity<?> findSkinListByTeamType(TeamType teamType);
}
