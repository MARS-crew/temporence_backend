package mars.temporence.service;

import mars.temporence.dto.playerLog.RequestLogSaveDto;
import org.springframework.http.ResponseEntity;

public interface LogService {

    ResponseEntity<?> saveLog(RequestLogSaveDto dto);

    ResponseEntity<?> findLogList();

}
