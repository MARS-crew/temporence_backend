package mars.ourmindmaze.repository.dm;

import mars.ourmindmaze.domain.Dm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmJpaRepository extends JpaRepository<Dm, Long> {
}
