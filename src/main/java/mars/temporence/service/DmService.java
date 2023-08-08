package mars.temporence.service;

import mars.temporence.dto.dm.RequestDmSaveDto;
import org.springframework.http.ResponseEntity;

public interface DmService {

    ResponseEntity<?> sendDm(RequestDmSaveDto dto);

    ResponseEntity<?> findDm(Long id);

}
