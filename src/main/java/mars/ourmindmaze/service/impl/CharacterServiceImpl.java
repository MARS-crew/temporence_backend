package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Character;
import mars.ourmindmaze.dto.character.RequestCharacterSaveDto;
import mars.ourmindmaze.dto.character.RequestCharacterUpdateDto;
import mars.ourmindmaze.repository.CharacterJpaRepository;
import mars.ourmindmaze.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto) {
        Optional<Character> findCharacter = characterJpaRepository.findByName(dto.getName());

        if (!findCharacter.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.BAD_REQUEST).message("같은 이름의 캐릭터가 존재합니다.").buildObject();
        }

        characterJpaRepository.save(Character.builder().name(dto.getName().trim()).teamType(dto.getTeamType()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "캐릭터가 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> findCharacterById(Long id) {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("캐릭터를 찾을 수 없습니다.").buildObject();
        }

        return CommonResponse.createResponse(HttpStatus.OK.value(), "캐릭터를 조회합니다.", findCharacter.get());
    }

    @Override
    public ResponseEntity<?> findCharacter() {
        List<Character> characterList = characterJpaRepository.findAll();

        return CommonResponse.createResponse(HttpStatus.OK.value(), "캐릭터 리스트를 조회합니다.", characterList);
    }

    @Override
    public ResponseEntity<?> updateCharacter(RequestCharacterUpdateDto dto, Long id) {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("캐릭터를 찾을 수 없습니다.").buildObject();
        }

        characterJpaRepository.updateCharacter(dto.getName().trim(), findCharacter.get().getId());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "캐릭터를 변경하였습니다.");
    }

    @Override
    public ResponseEntity<?> deleteCharacter(Long id) {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("캐릭터를 찾을 수 없습니다.").buildObject();
        }

        characterJpaRepository.delete(findCharacter.get());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "캐릭터를 삭제하였습니다.");
    }
}