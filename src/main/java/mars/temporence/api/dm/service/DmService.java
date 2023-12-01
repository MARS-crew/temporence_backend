package mars.temporence.api.dm.service;

import mars.temporence.api.dm.event.dto.RequestDmSaveDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface DmService {

    ResponseEntity<?> sendDm(RequestDmSaveDto dto, UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> findDm(Long id, UserDetailDto userDetailDto) throws Exception;

}
