package mars.temporence.repository.playerLog;

import mars.temporence.domain.Player;
import mars.temporence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p where p.user = :user and p.createdDate > :threeMonthAgo")
    List<Player> findPlayersByUser(User user, LocalDateTime threeMonthAgo);

    List<Player> findPlayersByLog_Id(Long id);

}
