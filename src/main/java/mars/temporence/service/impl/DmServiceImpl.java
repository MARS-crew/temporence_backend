package mars.temporence.service.impl;

import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.CommonResponse;
import mars.temporence.domain.Dm;
import mars.temporence.domain.User;
import mars.temporence.dto.dm.RequestDmSaveDto;
import mars.temporence.repository.dm.DmJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import mars.temporence.service.DmService;
import mars.temporence.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DmServiceImpl implements DmService {

    private final UserJpaRepository userJpaRepository;

    private final DmJpaRepository dmJpaRepository;

    @Override
    public ResponseEntity<?> sendDm(RequestDmSaveDto dto) {
        User sender = SecurityUtil.getCurrentUserId(userJpaRepository);

        if (sender.getId() == dto.getReciverId()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("본인에게 메시지를 보낼 수 없습니다.").buildObject();
        }

        Optional<User> reciver = userJpaRepository.findById(dto.getReciverId());

        if (reciver.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }

        if(dto.getContent().isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("내용을 입력해주세요.").buildObject();
        }

        dmJpaRepository.save(Dm.builder().sender(sender).reciver(reciver.get()).content(dto.getContent()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "메시지 전송에 성공하였습니다.");
    }

    @Override
    public ResponseEntity<?> findDm(Long id) {

        User user = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<User> friend = userJpaRepository.findById(id);

        if (friend.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }

        List<Dm> dms = dmJpaRepository.findDms(user, friend.get());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "Dm 조회에 성공하였습니다.", dms);
    }

}