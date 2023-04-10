package mars.ourmindmaze.repository;

import mars.ourmindmaze.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long idx);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(String name);
}
