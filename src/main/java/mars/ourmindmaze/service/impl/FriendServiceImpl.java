package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.repository.friend.FriendJpaRepository;
import mars.ourmindmaze.service.FriendService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendJpaRepository friendJpaRepository;
}
