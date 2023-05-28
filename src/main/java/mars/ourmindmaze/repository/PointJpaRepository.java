package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Point;
import mars.ourmindmaze.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUser(User user);
}
