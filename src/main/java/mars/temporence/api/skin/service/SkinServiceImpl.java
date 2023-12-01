package mars.temporence.api.skin.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.ApiResponse;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.api.skin.domain.Skin;
import mars.temporence.api.skin.event.dto.RequestSkinSaveDto;
import mars.temporence.global.enums.TeamType;
import mars.temporence.api.character.repository.CharacterJpaRepository;
import mars.temporence.api.skin.repository.SkinJpaRepository;
import mars.temporence.api.skin.event.vo.SkinVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkinServiceImpl implements SkinService {
    private final SkinJpaRepository skinJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public ResponseEntity<?> saveSkin(RequestSkinSaveDto dto) {
        Optional<Skin> findSkin = skinJpaRepository.findByName(dto.getName());

        if (!findSkin.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.BAD_REQUEST).message("같은 이름의 스킨이 존재합니다.").buildObject();
        }

        skinJpaRepository.save(Skin.builder().name(dto.getName()).teamType(dto.getTeamType()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "스킨이 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> findSkinById(Long id) {
        Optional<Skin> findSkin = skinJpaRepository.findById(id);

        if (findSkin.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("스킨을 찾을 수 없습니다.").buildObject();
        }

        System.out.println(findSkin.get());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "스킨을 조회합니다.", findSkin.get());
    }

    @Override
    public ResponseEntity<?> deleteSkin(Long id) {
        Optional<Skin> findSkin = skinJpaRepository.findById(id);

        if (findSkin.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("스킨을 찾을 수 없습니다.").buildObject();
        }

        skinJpaRepository.delete(findSkin.get());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "스킨을 삭제하였습니다.");
    }

    @Override
    public ResponseEntity<?> findSkinList() {
        List<SkinVO> response = skinJpaRepository.findSkinList();

        return CommonResponse.createResponse(HttpStatus.OK.value(), "스킨의 리스트를 조회합니다.", response);
    }

    @Override
    public ResponseEntity<?> findSkinListByTeamType(TeamType teamType) {
        List<SkinVO> response = skinJpaRepository.findSkinListByTeamType(teamType);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "스킨의 리스트를 조회합니다.", response);
    }
}
