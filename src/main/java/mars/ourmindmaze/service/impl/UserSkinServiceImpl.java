package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Skin;
import mars.ourmindmaze.domain.User;
import mars.ourmindmaze.domain.UserSkin;
import mars.ourmindmaze.dto.userSkin.RequestUserSkinSaveDto;
import mars.ourmindmaze.repository.skin.SkinJpaRepository;
import mars.ourmindmaze.repository.user.UserJpaRepository;
import mars.ourmindmaze.repository.userSkin.UserSkinJpaRepository;
import mars.ourmindmaze.service.UserSkinService;
import mars.ourmindmaze.util.SecurityUtil;
import mars.ourmindmaze.vo.UserSkinVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSkinServiceImpl implements UserSkinService {
    private final UserSkinJpaRepository userSkinJpaRepository;
    private final SkinJpaRepository skinJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public ResponseEntity<?> saveUserSkin(RequestUserSkinSaveDto dto) {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        Optional<Skin> findSkin = skinJpaRepository.findById(dto.getSkinId());

        if (findSkin.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("스킨을 찾을 수 없습니다.").buildObject();
        }

        userSkinJpaRepository.save(UserSkin.builder().skin(findSkin.get()).user(loginUser).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "유저의 스킨이 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> findUserSkinList() {
        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);

        List<UserSkinVO> list = userSkinJpaRepository.findUserSkinList(loginUser.getId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "유저의 스킨 리스트를 조회합니다.", list);
    }

    @Override
    public ResponseEntity<?> deleteUserSkin(Long id) {
        Optional<UserSkin> findUserSkin = userSkinJpaRepository.findById(id);

        if (findUserSkin.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("유저의 스킨을 찾을 수 없습니다.").buildObject();
        }

        userSkinJpaRepository.delete(findUserSkin.get());
        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "유저의 스킨을 삭제하였습니다.");
    }
}
