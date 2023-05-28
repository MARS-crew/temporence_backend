package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Friend;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.dto.friend.RequestFriendSaveDto;
import mars.ourmindmaze.enums.ExceptionEnum;
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
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);
        Optional<User> findUser = userJpaRepository.findById(dto.getFriendId());

        if (findUser.isEmpty()) {
            return ApiResponse.<Object>builder().ApiResponseBuilder(ExceptionEnum.NOT_FOUDN_USER).buildObject();
        }

        friendJpaRepository.save(Friend.builder().user(loginUser).friend(findUser.get()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "친구 등록에 성공하였습니다.");
    }

    @Override
    public ResponseEntity<?> findFriendList() {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<FriendVO> list = friendJpaRepository.findFriendList(loginUser.getId());
        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 리스트를 조회합니다.", list);
    }
}
