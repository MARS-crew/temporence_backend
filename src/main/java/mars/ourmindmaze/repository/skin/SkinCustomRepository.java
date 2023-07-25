package mars.ourmindmaze.repository.skin;

import mars.ourmindmaze.enums.TeamType;
import mars.ourmindmaze.vo.SkinVO;

import java.util.List;

public interface SkinCustomRepository {
    List<SkinVO> findSkinList();
    List<SkinVO> findSkinListByTeamType(TeamType teamType);
}
