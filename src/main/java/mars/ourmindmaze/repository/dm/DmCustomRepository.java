package mars.ourmindmaze.repository.dm;

import mars.ourmindmaze.vo.DmVO;
import mars.ourmindmaze.vo.UserDmVO;

import java.util.List;

public interface DmCustomRepository {
    List<DmVO> findDmList(Long sendId, Long reciverId);
    List<UserDmVO> findDmUserList(Long id);
}
