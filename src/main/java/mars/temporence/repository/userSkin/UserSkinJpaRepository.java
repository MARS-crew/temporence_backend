package mars.temporence.repository.userSkin;

import mars.temporence.domain.UserSkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSkinJpaRepository extends JpaRepository<UserSkin, Long>, UserSkinCustomRepository {
    Optional<UserSkin> findById(Long id);
}
