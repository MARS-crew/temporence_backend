package mars.temporence.api.friend.service;

import mars.temporence.api.friend.event.dto.RequestFriendSaveDto;
import mars.temporence.api.friend.event.dto.RequestFriendUpdateDto;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    ResponseEntity<?> saveFriend(RequestFriendSaveDto dto) throws Exception;

    ResponseEntity<?> findFriendList() throws Exception;

    ResponseEntity<?> deleteFriend(Long id) throws Exception;

    ResponseEntity<?> findFriendRequestList() throws Exception;

    ResponseEntity<?> updateFriend(RequestFriendUpdateDto dto) throws Exception;
}
