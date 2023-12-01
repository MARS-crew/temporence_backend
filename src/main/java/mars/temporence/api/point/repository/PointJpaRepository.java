package mars.temporence.api.point.repository;

import mars.temporence.api.point.domain.Point;
import mars.temporence.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUser(User user);

    @Modifying
    @Transactional
    @Query("UPDATE Point p SET p.blue = :point WHERE p.user.id = :userId")
    int updateBluePoint(@Param("point") Integer point, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Point p SET p.gold = :point WHERE p.user.id = :userId")
    int updateGoldPoint(@Param("point") Integer point, @Param("userId") Long userId);
}
