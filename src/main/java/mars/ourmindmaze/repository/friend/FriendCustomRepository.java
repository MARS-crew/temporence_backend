package mars.ourmindmaze.repository.friend;

import mars.ourmindmaze.vo.FriendVO;

import java.util.List;

public interface FriendCustomRepository {
    List<FriendVO> findFriendList(Long userId);
    List<FriendVO> findFriendRequestList(Long userId);
}
