package mars.ourmindmaze.repository;
import mars.ourmindmaze.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshJpaTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByAuthKeyAndType(String authKey, String type);
}
