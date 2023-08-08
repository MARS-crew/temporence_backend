package mars.temporence.repository.skin;

import mars.temporence.domain.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkinJpaRepository extends JpaRepository<Skin, Long>, SkinCustomRepository {
    Optional<Skin> findByName(String name);
    Optional<Skin> findById(Long id);
}
