package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJpaRepository extends JpaRepository<Point, Long> {}
