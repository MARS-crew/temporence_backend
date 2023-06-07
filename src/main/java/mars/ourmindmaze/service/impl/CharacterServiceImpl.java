package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Character;
import mars.ourmindmaze.dto.character.RequestCharacterSaveDto;
import mars.ourmindmaze.repository.CharacterJpaRepository;
import mars.ourmindmaze.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto) {
        Optional<Character> findCharacter = characterJpaRepository.findByName(dto.getName());

        if(!findCharacter.isEmpty()){
            return ApiResponse.<Objects>builder().status(HttpStatus.BAD_REQUEST).message("같은 이름의 캐릭터가 존재합니다.").buildObject();
        }

        characterJpaRepository.save(Character.builder().name(dto.getName()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "캐릭터가 등록되었습니다.");
    }
}
