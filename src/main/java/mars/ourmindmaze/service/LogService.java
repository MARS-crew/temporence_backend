package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.log.RequestLogSaveDto;
import org.springframework.http.ResponseEntity;

public interface LogService {

    ResponseEntity<?> saveLog(RequestLogSaveDto dto);

    ResponseEntity<?> findLogList();

}
