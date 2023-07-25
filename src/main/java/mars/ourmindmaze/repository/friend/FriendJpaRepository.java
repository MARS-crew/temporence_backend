package mars.ourmindmaze.repository.friend;

import mars.ourmindmaze.domain.Friend;
import mars.ourmindmaze.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FriendJpaRepository extends JpaRepository<Friend, Long>, FriendCustomRepository {
    Optional<Friend> findById(Long id);
    Optional<Friend> findByUserAndFriend(User user, User friend);

    @Modifying
    @Transactional
    @Query("UPDATE Friend f SET f.status = :status WHERE f.id = :friendId")
    int updateFriendStatus(@Param("status") String status, @Param("friendId") Long friendId);
}
