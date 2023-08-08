package mars.temporence.repository.dm;

import mars.temporence.domain.Dm;
import mars.temporence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DmJpaRepository extends JpaRepository<Dm, Long> {

    @Query("select d from Dm d where d.reciver in (:user, :friend) or d.sender in (:user, :friend)")
    List<Dm> findDms(User user, User friend);

}
