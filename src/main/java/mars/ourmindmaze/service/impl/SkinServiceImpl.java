package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Character;
import mars.ourmindmaze.domain.Skin;
import mars.ourmindmaze.dto.skin.RequestSkinSaveDto;
import mars.ourmindmaze.repository.CharacterJpaRepository;
import mars.ourmindmaze.repository.SkinJpaRepository;
import mars.ourmindmaze.service.SkinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        Optional<Character> findCharacter = characterJpaRepository.findById(dto.getCharacterId());

        if (findCharacter.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("캐릭터를 찾을 수 없습니다.").buildObject();
        }

        skinJpaRepository.save(Skin.builder().name(dto.getName()).character(findCharacter.get()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "스킨이 등록되었습니다.");
    }
}
