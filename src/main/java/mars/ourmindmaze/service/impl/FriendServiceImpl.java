package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Friend;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.friend.RequestFriendSaveDto;
import mars.ourmindmaze.repository.friend.FriendJpaRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.service.FriendService;
import mars.ourmindmaze.util.SecurityUtil;
import mars.ourmindmaze.vo.FriendVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserJpaRepository userJpaRepository;
    private final FriendJpaRepository friendJpaRepository;

    @Override
    @Transactional
    public ResponseEntity<?> saveFriend(RequestFriendSaveDto dto) {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        if (loginUser.getId() == dto.getFriendId()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("본인은 친구를 추가할 수 없습니다.").buildObject();
        }

        Optional<User> findUser = userJpaRepository.findById(dto.getFriendId());

        if (findUser.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("유저를 찾을 수 없습니다.").buildObject();
        }

        friendJpaRepository.save(Friend.builder().user(loginUser).friend(findUser.get()).build());
        friendJpaRepository.save(Friend.builder().user(findUser.get()).friend(loginUser).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "친구 등록에 성공하였습니다.");
    }

    @Override
    public ResponseEntity<?> findFriendList() {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<FriendVO> list = friendJpaRepository.findFriendList(loginUser.getId());
        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 리스트를 조회합니다.", list);
    }

    @Override
    public ResponseEntity<?> deleteFriend(Long id) {
        Optional<Friend> findFriend = friendJpaRepository.findById(id);
        if (findFriend.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("친구를 찾을 수 없습니다.").buildObject();
        }

        friendJpaRepository.delete(findFriend.get());
        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구가 삭제가 되었습니다.");
    }
}
