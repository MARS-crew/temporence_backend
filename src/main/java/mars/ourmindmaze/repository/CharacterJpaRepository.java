package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterJpaRepository extends JpaRepository<Character, Long> {
}
