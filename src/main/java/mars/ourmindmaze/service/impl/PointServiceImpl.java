package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Point;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.repository.PointJpaRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.PointService;
import mars.ourmindmaze.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointJpaRepository pointJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public ResponseEntity<?> findMyPoint() {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<Point> findPoint = pointJpaRepository.findByUser(loginUser);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "포인트를 조회합니다.", findPoint);
    }
}
