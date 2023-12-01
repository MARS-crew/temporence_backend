package mars.temporence.api.skin.repository;

import mars.temporence.global.enums.TeamType;
import mars.temporence.api.skin.event.vo.SkinVO;

import java.util.List;

public interface SkinCustomRepository {
    List<SkinVO> findSkinList();
    List<SkinVO> findSkinListByTeamType(TeamType teamType);
}
