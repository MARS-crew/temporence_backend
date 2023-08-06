package mars.ourmindmaze.repository.playerLog;

import mars.ourmindmaze.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogJpaRepository extends JpaRepository<Log, Long> {
}
