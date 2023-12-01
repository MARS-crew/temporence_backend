package mars.temporence.api.user.repository;

import mars.temporence.api.user.event.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page<UserVO> findAllUser(Pageable pageable);
}
