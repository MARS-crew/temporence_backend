package mars.temporence.api.point.service;

import mars.temporence.api.point.event.dto.RequestPointUpdateDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface PointService {
    ResponseEntity<?> findMyPoint(UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> updatePoint(RequestPointUpdateDto dto, UserDetailDto userDetailDto) throws Exception;
}
