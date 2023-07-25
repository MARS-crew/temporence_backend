package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.friend.RequestFriendSaveDto;
import mars.ourmindmaze.dto.friend.RequestFriendUpdateDto;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    ResponseEntity<?> saveFriend(RequestFriendSaveDto dto);
    ResponseEntity<?> findFriendList();
    ResponseEntity<?> deleteFriend(Long id);
    ResponseEntity<?> findFriendRequestList();
    ResponseEntity<?> updateFriend(RequestFriendUpdateDto dto);
}
