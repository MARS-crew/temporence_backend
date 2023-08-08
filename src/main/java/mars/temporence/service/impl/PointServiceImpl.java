package mars.temporence.service.impl;

import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.CommonResponse;
import mars.temporence.domain.Point;
import mars.temporence.domain.User;
import mars.temporence.dto.point.RequestPointUpdateDto;
import mars.temporence.enums.PointType;
import mars.temporence.repository.PointJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import mars.temporence.service.PointService;
import mars.temporence.util.SecurityUtil;
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

    @Override
    public ResponseEntity<?> updatePoint(RequestPointUpdateDto dto) {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<Point> findPoint = pointJpaRepository.findByUser(loginUser);

        if(dto.getPointType() == PointType.GOLD){
            pointJpaRepository.updateGoldPoint(findPoint.get().getGold() + dto.getPoint(), loginUser.getId());
        }

        if(dto.getPointType() == PointType.BLUE){
            pointJpaRepository.updateBluePoint(findPoint.get().getBlue() + dto.getPoint(), loginUser.getId());
        }

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "포인트를 수정합니다.");
    }
}
