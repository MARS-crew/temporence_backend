package mars.ourmindmaze.repository.userSkin;

import mars.ourmindmaze.domain.UserSkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSkinJpaRepository extends JpaRepository<UserSkin, Long>, UserSkinCustomRepository {
    Optional<UserSkin> findById(Long id);
}
