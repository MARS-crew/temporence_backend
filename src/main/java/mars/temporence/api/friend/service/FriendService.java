package mars.temporence.api.friend.service;

import mars.temporence.api.friend.event.dto.RequestFriendSaveDto;
import mars.temporence.api.friend.event.dto.RequestFriendUpdateDto;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    ResponseEntity<?> saveFriend(RequestFriendSaveDto dto);
    ResponseEntity<?> findFriendList();
    ResponseEntity<?> deleteFriend(Long id);
    ResponseEntity<?> findFriendRequestList();
    ResponseEntity<?> updateFriend(RequestFriendUpdateDto dto);
}
