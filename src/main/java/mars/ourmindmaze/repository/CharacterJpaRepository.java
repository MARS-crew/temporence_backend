package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterJpaRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByName(String name);
}
