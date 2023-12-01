package mars.temporence.api.point.service;

import mars.temporence.api.point.event.dto.RequestPointUpdateDto;
import org.springframework.http.ResponseEntity;

public interface PointService {
    ResponseEntity<?> findMyPoint() throws Exception;

    ResponseEntity<?> updatePoint(RequestPointUpdateDto dto) throws Exception;
}
