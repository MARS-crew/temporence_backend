package mars.ourmindmaze.repository.friend;

import mars.ourmindmaze.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendJpaRepository extends JpaRepository<Friend, Long>, FriendCustomRepository {
    Optional<Friend> findById(Long id);
}
