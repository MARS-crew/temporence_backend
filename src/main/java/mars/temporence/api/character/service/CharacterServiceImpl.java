package mars.temporence.api.character.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.api.character.event.dto.RequestCharacterSaveDto;
import mars.temporence.api.character.event.dto.RequestCharacterUpdateDto;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.api.character.domain.Character;
import mars.temporence.api.character.repository.CharacterJpaRepository;
import mars.temporence.global.exception.BadRequestException;
import mars.temporence.global.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto) throws Exception {
        Optional<Character> findCharacter = characterJpaRepository.findByName(dto.getName());

        if (!findCharacter.isEmpty()) {
            throw new BadRequestException("같은 이름의 캐릭터가 존재합니다.");
        }

        characterJpaRepository.save(Character.builder().name(dto.getName()).teamType(dto.getTeamType()).build());

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "캐릭터가 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> findCharacterById(Long id) throws Exception {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            throw new NotFoundException("캐릭터를 찾을 수 없습니다.");
        }

        return CommonResponse.createResponse(HttpStatus.OK.value(), "캐릭터를 조회합니다.", findCharacter.get());
    }

    @Override
    public ResponseEntity<?> findCharacter() throws Exception {
        List<Character> characterList = characterJpaRepository.findAll();

        return CommonResponse.createResponse(HttpStatus.OK.value(), "캐릭터 리스트를 조회합니다.", characterList);
    }

    @Override
    public ResponseEntity<?> updateCharacter(RequestCharacterUpdateDto dto, Long id) throws Exception {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            throw new NotFoundException("캐릭터를 찾을 수 없습니다.");
        }

        characterJpaRepository.updateCharacter(dto.getName(), findCharacter.get().getId());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "캐릭터를 변경하였습니다.");
    }

    @Override
    public ResponseEntity<?> deleteCharacter(Long id) throws Exception {
        Optional<Character> findCharacter = characterJpaRepository.findById(id);

        if (findCharacter.isEmpty()) {
            throw new NotFoundException("캐릭터를 찾을 수 없습니다.");
        }

        characterJpaRepository.delete(findCharacter.get());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "캐릭터를 삭제하였습니다.");
    }
}