package mars.temporence.repository.friend;

import mars.temporence.vo.FriendVO;

import java.util.List;

public interface FriendCustomRepository {
    List<FriendVO> findFriendList(Long userId);
    List<FriendVO> findFriendRequestList(Long userId);
}
