package mars.temporence.api.dm.service;

import mars.temporence.api.dm.event.dto.RequestDmSaveDto;
import org.springframework.http.ResponseEntity;

public interface DmService {

    ResponseEntity<?> sendDm(RequestDmSaveDto dto);

    ResponseEntity<?> findDm(Long id);

}
