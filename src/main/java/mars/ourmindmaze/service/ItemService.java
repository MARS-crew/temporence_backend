package mars.ourmindmaze.service;

import mars.ourmindmaze.dto.item.RequestItemSaveDto;
import org.springframework.http.ResponseEntity;

public interface ItemService {

    ResponseEntity<?> saveItem(RequestItemSaveDto dto);

    ResponseEntity<?> updateItemType(RequestItemSaveDto dto, Long id);

    ResponseEntity<?> updatePointType(RequestItemSaveDto dto, Long id);
    ResponseEntity<?> updateItem(RequestItemSaveDto dto, Long id);
    ResponseEntity<?> updateItemCost(RequestItemSaveDto dto, Long id);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> findItemById(Long id);
}
