package mars.temporence.api.friend.repository;

import mars.temporence.api.friend.event.vo.FriendVO;

import java.util.List;

public interface FriendCustomRepository {
    List<FriendVO> findFriendList(Long userId);
    List<FriendVO> findFriendRequestList(Long userId);
}
