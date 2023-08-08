package mars.temporence.service.impl;

import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.CommonResponse;
import mars.temporence.domain.Skin;
import mars.temporence.domain.User;
import mars.temporence.domain.UserSkin;
import mars.temporence.dto.userSkin.RequestUserSkinSaveDto;
import mars.temporence.repository.skin.SkinJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import mars.temporence.repository.userSkin.UserSkinJpaRepository;
import mars.temporence.service.UserSkinService;
import mars.temporence.util.SecurityUtil;
import mars.temporence.vo.UserSkinVO;
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