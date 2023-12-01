package mars.temporence.api.friend.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.api.friend.domain.Friend;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.friend.event.dto.RequestFriendSaveDto;
import mars.temporence.api.friend.event.dto.RequestFriendUpdateDto;
import mars.temporence.api.friend.repository.FriendJpaRepository;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
import mars.temporence.global.util.SecurityUtil;
import mars.temporence.api.friend.event.vo.FriendVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserJpaRepository userJpaRepository;
    private final FriendJpaRepository friendJpaRepository;

    @Override
    public ResponseEntity<?> saveFriend(RequestFriendSaveDto dto) throws Exception {
        Optional<User> findUser = userJpaRepository.findByNickname(dto.getNickname());

        if (findUser.isEmpty()) {
            throw new NotFoundException("친구 할 유저를 찾을 수 없습니다.");
        }

        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        if (loginUser.getNickname().equals(dto.getNickname())) {
            throw new BadRequestException("본인은 친구를 추가할 수 없습니다.");
        }

        Optional<Friend> findFriend = friendJpaRepository.findByUserAndFriend(loginUser, findUser.get());

        if (!findFriend.isEmpty()) {
            if (findFriend.get().getStatus().equals("Y")) {
                throw new BadRequestException("이미 친구 입니다.");
            }
            throw new BadRequestException("이미 친구 요청을 보낸 대상 입니다.");
        }

        friendJpaRepository.save(Friend.builder().user(loginUser).friend(findUser.get()).status("N").build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "친구 등록에 성공하였습니다.");
    }

    @Override
    public ResponseEntity<?> findFriendList() throws Exception {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<FriendVO> list = friendJpaRepository.findFriendList(loginUser.getId());
        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 리스트를 조회합니다.", list);
    }

    @Override
    public ResponseEntity<?> deleteFriend(Long id) throws Exception {
        Optional<Friend> findFriend = friendJpaRepository.findById(id);
        if (findFriend.isEmpty()) {
            throw new NotFoundException("친구를 찾을 수 없습니다.");
        }

        friendJpaRepository.delete(findFriend.get());
        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구가 삭제가 되었습니다.");
    }

    @Override
    public ResponseEntity<?> findFriendRequestList() throws Exception {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<FriendVO> list = friendJpaRepository.findFriendList(loginUser.getId());
        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 요청 리스트를 조회합니다.", list);
    }

    @Override
    public ResponseEntity<?> updateFriend(RequestFriendUpdateDto dto) throws Exception {
        Optional<Friend> findFriend = friendJpaRepository.findById(dto.getFriendId());
        if (findFriend.isEmpty()) {
            throw new NotFoundException("친구를 찾을 수 없습니다.");
        }

        if (dto.getStatus().equals("Y")) {
            friendJpaRepository.updateFriendStatus(dto.getStatus(), dto.getFriendId());
            return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구 요청을 수락했습니다.");
        }

        friendJpaRepository.delete(findFriend.get());
        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구 요청을 거절했습니다.");
    }
}
