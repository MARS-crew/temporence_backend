package mars.temporence.repository.userSkin;

import mars.temporence.vo.UserSkinVO;

import java.util.List;

public interface UserSkinCustomRepository {
    List<UserSkinVO> findUserSkinList(Long id);
}
