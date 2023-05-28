package mars.ourmindmaze.repository.dm;

import mars.ourmindmaze.domain.DM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmJpaRepository extends JpaRepository<DM, Long>, DmCustomRepository {
}
