package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CharacterJpaRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByName(String name);
    Optional<Character> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Character c SET c.name = :name WHERE c.id = :id")
    int updateCharacter(@Param("name") String name, @Param("id") Long id);
}
