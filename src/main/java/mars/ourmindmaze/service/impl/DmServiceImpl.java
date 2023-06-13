package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.DM;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.dm.RequestDmSaveDto;
import mars.ourmindmaze.repository.dm.DmJpaRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.DmService;
import mars.ourmindmaze.util.SecurityUtil;
import mars.ourmindmaze.vo.DmVO;
import mars.ourmindmaze.vo.UserDmVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DmServiceImpl implements DmService {
    private final DmJpaRepository dmJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public ResponseEntity<?> saveDm(RequestDmSaveDto dto) {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<User> reciver = userJpaRepository.findById(dto.getReciverId());
        if (reciver.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }
        dmJpaRepository.save(DM.builder().reciver(reciver.get()).sender(loginUser).content(dto.getContent()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "메세지를 보냈습니다.");
    }

    @Override
    public ResponseEntity<?> findDm(Long id) {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<User> reciver = userJpaRepository.findById(id);
        if (reciver.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }

        List<DmVO> list = dmJpaRepository.findDmList(loginUser.getId(), reciver.get().getId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "메세지 리스트를 출력합니다.", list);
    }

    @Override
    public ResponseEntity<?> findUserList() {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<UserDmVO> list = dmJpaRepository.findDmUserList(loginUser.getId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "메세지 대상을 출력합니다.", list);
    }
}
