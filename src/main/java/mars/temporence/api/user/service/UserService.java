package mars.temporence.api.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> findAll(Pageable pageable) throws Exception;
}
