package mars.temporence.repository.user;

import mars.temporence.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page<UserVO> findAllUser(Pageable pageable);
}
