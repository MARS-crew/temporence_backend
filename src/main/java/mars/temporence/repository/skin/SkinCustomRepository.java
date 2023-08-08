package mars.temporence.repository.skin;

import mars.temporence.enums.TeamType;
import mars.temporence.vo.SkinVO;

import java.util.List;

public interface SkinCustomRepository {
    List<SkinVO> findSkinList();
    List<SkinVO> findSkinListByTeamType(TeamType teamType);
}
