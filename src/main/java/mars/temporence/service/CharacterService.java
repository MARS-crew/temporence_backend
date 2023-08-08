package mars.temporence.service;

import mars.temporence.dto.character.RequestCharacterSaveDto;
import mars.temporence.dto.character.RequestCharacterUpdateDto;
import org.springframework.http.ResponseEntity;

public interface CharacterService {
    ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto);
    ResponseEntity<?> findCharacterById(Long id);
    ResponseEntity<?> findCharacter();
    ResponseEntity<?> updateCharacter(RequestCharacterUpdateDto dto, Long id);
    ResponseEntity<?> deleteCharacter(Long id);
}
