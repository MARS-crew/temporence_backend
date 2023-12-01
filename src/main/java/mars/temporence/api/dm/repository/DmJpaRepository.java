package mars.temporence.api.dm.repository;

import mars.temporence.api.dm.domain.Dm;
import mars.temporence.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DmJpaRepository extends JpaRepository<Dm, Long> {

    @Query("select d from Dm d where d.reciver in (:user, :friend) or d.sender in (:user, :friend)")
    List<Dm> findDms(User user, User friend);

}
