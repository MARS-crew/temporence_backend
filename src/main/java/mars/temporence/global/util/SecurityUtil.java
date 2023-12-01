package mars.temporence.global.util;


import lombok.extern.slf4j.Slf4j;
import mars.temporence.api.user.domain.User;
import mars.temporence.api.user.repository.UserJpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class SecurityUtil {
    public static User getCurrentUserId(UserJpaRepository userJpaRepository) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }

        Optional<User> findUser = userJpaRepository.findByUsername(authentication.getName());
        if(findUser.isEmpty()){
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
        return findUser.get();
    }
}
