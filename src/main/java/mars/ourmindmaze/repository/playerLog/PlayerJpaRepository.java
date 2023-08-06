package mars.ourmindmaze.repository.playerLog;

import mars.ourmindmaze.domain.Player;
import mars.ourmindmaze.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p where p.user = :user and p.createdDate > :threeMonthAgo")
    List<Player> findPlayersByUser(User user, LocalDateTime threeMonthAgo);

    List<Player> findPlayersByLog_Id(Long id);

}
