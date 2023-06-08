package mars.ourmindmaze.repository.skin;

import mars.ourmindmaze.vo.SkinVO;

import java.util.List;

public interface SkinCustomRepository {
    List<SkinVO> findSkinList();
    List<SkinVO> findSkinListByCharacter(Long id);
}
