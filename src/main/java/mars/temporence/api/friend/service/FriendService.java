package mars.temporence.api.friend.service;

import mars.temporence.api.friend.event.dto.RequestFriendSaveDto;
import mars.temporence.api.friend.event.dto.RequestFriendUpdateDto;
import mars.temporence.global.dto.UserDetailDto;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    ResponseEntity<?> saveFriend(RequestFriendSaveDto dto, UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> findFriendList(UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> deleteFriend(Long id) throws Exception;

    ResponseEntity<?> findFriendRequestList(UserDetailDto userDetailDto) throws Exception;

    ResponseEntity<?> updateFriend(RequestFriendUpdateDto dto) throws Exception;
}
