package mars.temporence.api.dm.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.api.dm.domain.Dm;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.dm.event.dto.RequestDmSaveDto;
import mars.temporence.api.dm.repository.DmJpaRepository;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.global.dto.UserDetailDto;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
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
    public ResponseEntity<?> sendDm(RequestDmSaveDto dto, UserDetailDto userDetailDto) throws Exception {
        Optional<User> sender = userJpaRepository.findById(userDetailDto.getUserId());

        if(sender.isEmpty()){
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        if (sender.get().getId() == dto.getReciverId()) {
            throw new BadRequestException("본인에게 메세지를 보낼 수 없습니다.");
        }

        Optional<User> reciver = userJpaRepository.findById(dto.getReciverId());

        if (reciver.isEmpty()) {
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        if (dto.getContent().isEmpty()) {
            throw new BadRequestException("내용을 입력해주세요.");
        }

        dmJpaRepository.save(Dm.builder().sender(sender.get()).reciver(reciver.get()).content(dto.getContent()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "메시지 전송에 성공하였습니다.");
    }

    @Override
    public ResponseEntity<?> findDm(Long id, UserDetailDto userDetailDto) throws Exception {
        Optional<User> user = userJpaRepository.findById(userDetailDto.getUserId());

        if(user.isEmpty()){
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }


        Optional<User> friend = userJpaRepository.findById(id);

        if (friend.isEmpty()) {
            throw new NotFoundException("유저를 찾을 수 없습니다.");
        }

        List<Dm> dms = dmJpaRepository.findDms(user.get(), friend.get());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "Dm 조회에 성공하였습니다.", dms);
    }

}
