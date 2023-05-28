package mars.ourmindmaze.repository.friend;

import mars.ourmindmaze.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendJpaRepository extends JpaRepository<Friend, Long>, FriendCustomRepository {
}
