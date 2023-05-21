package mars.ourmindmaze.repository.user;

import mars.ourmindmaze.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {
    List<User> findAll();
}
