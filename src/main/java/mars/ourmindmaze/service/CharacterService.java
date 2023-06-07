package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.character.RequestCharacterSaveDto;
import org.springframework.http.ResponseEntity;

public interface CharacterService {
    ResponseEntity<?> saveCharacter(RequestCharacterSaveDto dto);
}
