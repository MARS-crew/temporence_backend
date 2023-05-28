package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.DM;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.dm.RequestDmSaveDto;
import mars.ourmindmaze.enums.ExceptionEnum;
import mars.ourmindmaze.repository.dm.DmJpaRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.DmService;
import mars.ourmindmaze.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_FOUDN_USER).buildObject();
        }
        dmJpaRepository.save(DM.builder().reciver(reciver.get()).sender(loginUser).content(dto.getContent()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "메세지를 보냈습니다.");
    }
}
