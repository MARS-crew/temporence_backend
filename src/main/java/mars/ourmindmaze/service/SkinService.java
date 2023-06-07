package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.skin.RequestSkinSaveDto;
import org.springframework.http.ResponseEntity;

public interface SkinService {
    ResponseEntity<?> saveSkin(RequestSkinSaveDto dto);
    ResponseEntity<?> findSkinById(Long id);
    ResponseEntity<?> deleteSkin(Long id);
}
