package mars.temporence.api.skin.service;

import mars.temporence.api.skin.event.dto.RequestSkinSaveDto;
import mars.temporence.global.enums.TeamType;
import org.springframework.http.ResponseEntity;

public interface SkinService {
    ResponseEntity<?> saveSkin(RequestSkinSaveDto dto) throws Exception;

    ResponseEntity<?> findSkinById(Long id) throws Exception;

    ResponseEntity<?> deleteSkin(Long id) throws Exception;

    ResponseEntity<?> findSkinList() throws Exception;

    ResponseEntity<?> findSkinListByTeamType(TeamType teamType) throws Exception;
}
