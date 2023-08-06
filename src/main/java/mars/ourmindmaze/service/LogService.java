package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.playerLog.RequestLogSaveDto;
import org.springframework.http.ResponseEntity;

public interface LogService {

    ResponseEntity<?> saveLog(RequestLogSaveDto dto);

    ResponseEntity<?> findLogList();

}
