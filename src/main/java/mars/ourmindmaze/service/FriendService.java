package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.friend.RequestFriendSaveDto;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    ResponseEntity<?> saveFriend(RequestFriendSaveDto dto);
    ResponseEntity<?> findFriendList();
    ResponseEntity<?> deleteFriend(Long id);
}
