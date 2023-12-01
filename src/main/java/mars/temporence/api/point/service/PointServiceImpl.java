package mars.temporence.api.point.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.api.point.domain.Point;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.point.event.dto.RequestPointUpdateDto;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.enums.PointType;
import mars.temporence.api.point.repository.PointJpaRepository;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.global.exception.NotFoundException;
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
    public ResponseEntity<?> findMyPoint(UserDetailDto userDetailDto) throws Exception {
        Optional<User> loginUser = userJpaRepository.findById(userDetailDto.getUserId());
        if (loginUser.isEmpty()) {
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }
        Optional<Point> findPoint = pointJpaRepository.findByUser(loginUser.get());

        if (findPoint.isEmpty()) {
            throw new NotFoundException("포인트를 찾을 수 없습니다.");
        }

        return CommonResponse.createResponse(HttpStatus.OK.value(), "포인트를 조회합니다.", findPoint);
    }

    @Override
    public ResponseEntity<?> updatePoint(RequestPointUpdateDto dto, UserDetailDto userDetailDto) throws Exception {
        Optional<User> loginUser = userJpaRepository.findById(userDetailDto.getUserId());

        if (loginUser.isEmpty()) {
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        Optional<Point> findPoint = pointJpaRepository.findByUser(loginUser.get());

        if (dto.getPointType() == PointType.GOLD) {
            pointJpaRepository.updateGoldPoint(findPoint.get().getGold() + dto.getPoint(), loginUser.get().getId());
        }

        if (dto.getPointType() == PointType.BLUE) {
            pointJpaRepository.updateBluePoint(findPoint.get().getBlue() + dto.getPoint(), loginUser.get().getId());
        }

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "포인트를 수정합니다.");
    }
}
