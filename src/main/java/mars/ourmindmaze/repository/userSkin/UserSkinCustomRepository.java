package mars.ourmindmaze.repository.userSkin;

import mars.ourmindmaze.vo.UserSkinVO;

import java.util.List;

public interface UserSkinCustomRepository {
    List<UserSkinVO> findUserSkinList(Long id);
}
