package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.dm.RequestDmSaveDto;
import org.springframework.http.ResponseEntity;

public interface DmService {
    ResponseEntity<?> saveDm(RequestDmSaveDto dto);
}
