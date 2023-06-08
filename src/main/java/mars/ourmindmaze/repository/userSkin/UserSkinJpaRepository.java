package mars.ourmindmaze.repository.userSkin;

import mars.ourmindmaze.domain.UserSkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkinJpaRepository extends JpaRepository<UserSkin, Long>, UserSkinCustomRepository {
}
