package mars.temporence.api.character.service;

import mars.temporence.api.character.event.dto.RequestCharacterSaveDto;
import mars.temporence.api.character.event.dto.RequestCharacterUpdateDto;
import org.springframework.http.ResponseEntity;

public interface CharacterService {
    ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto) throws Exception;

    ResponseEntity<?> findCharacterById(Long id) throws Exception;

    ResponseEntity<?> findCharacter() throws Exception;

    ResponseEntity<?> updateCharacter(RequestCharacterUpdateDto dto, Long id) throws Exception;

    ResponseEntity<?> deleteCharacter(Long id) throws Exception;
}
