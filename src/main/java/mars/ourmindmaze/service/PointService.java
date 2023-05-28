package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.point.RequestPointUpdateDto;
import org.springframework.http.ResponseEntity;

public interface PointService {
    ResponseEntity<?> findMyPoint();
    ResponseEntity<?> updatePoint(RequestPointUpdateDto dto);
}
