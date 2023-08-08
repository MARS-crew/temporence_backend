package mars.temporence.service;

import mars.temporence.dto.point.RequestPointUpdateDto;
import org.springframework.http.ResponseEntity;

public interface PointService {
    ResponseEntity<?> findMyPoint();
    ResponseEntity<?> updatePoint(RequestPointUpdateDto dto);
}
