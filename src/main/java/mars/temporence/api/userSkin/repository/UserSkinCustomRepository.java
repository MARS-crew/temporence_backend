package mars.temporence.api.userSkin.repository;

import mars.temporence.api.userSkin.event.vo.UserSkinVO;

import java.util.List;

public interface UserSkinCustomRepository {
    List<UserSkinVO> findUserSkinList(Long id);
}
