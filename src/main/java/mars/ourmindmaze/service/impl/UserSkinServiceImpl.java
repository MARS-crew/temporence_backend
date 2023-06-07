package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.repository.userSkin.UserSkinJpaRepository;
import mars.ourmindmaze.service.UserSkinService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSkinServiceImpl implements UserSkinService {
    private final UserSkinJpaRepository userSkinJpaRepository;
}
