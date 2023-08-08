package mars.temporence.repository.playerLog;

import mars.temporence.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogJpaRepository extends JpaRepository<Log, Long> {
}
