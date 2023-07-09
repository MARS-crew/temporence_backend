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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final UserJpaRepository userJpaRepository;
    private final FriendJpaRepository friendJpaRepository;

    @Override
    public ResponseEntity<?> saveFriend(RequestFriendSaveDto dto) {
        Optional<User> findUser = userJpaRepository.findByNickname(dto.getNickname());

        if (findUser.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("친구 할 유저를 찾을 수 없습니다.").buildObject();
        }

        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        if (loginUser.getNickname().equals(dto.getNickname())) {
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("본인은 친구를 추가할 수 없습니다.").buildObject();
        }

        Optional<Friend> findFriend = friendJpaRepository.findByUserAndFriend(loginUser, findUser.get());

        if (!findFriend.isEmpty()) {
            if (findFriend.get().getStatus().equals("Y")) {
                return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("이미 친구 입니다.").buildObject();
            }
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("이미 친구 요청을 보낸 대상 입니다.").buildObject();
        }

        friendJpaRepository.save(Friend.builder().user(loginUser).friend(findUser.get()).status("N").build());

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
