package mars.ourmindmaze.repository.dm;

import mars.ourmindmaze.vo.DmVO;

import java.util.List;

public interface DmCustomRepository {
    List<DmVO> findDmList(Long sendId, Long reciverId);
}
