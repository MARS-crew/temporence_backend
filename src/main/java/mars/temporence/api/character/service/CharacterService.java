package mars.temporence.api.character.service;

import mars.temporence.api.character.event.dto.RequestCharacterSaveDto;
import mars.temporence.api.character.event.dto.RequestCharacterUpdateDto;
import org.springframework.http.ResponseEntity;

public interface CharacterService {
    ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto);
    ResponseEntity<?> findCharacterById(Long id);
    ResponseEntity<?> findCharacter();
    ResponseEntity<?> updateCharacter(RequestCharacterUpdateDto dto, Long id);
    ResponseEntity<?> deleteCharacter(Long id);
}
