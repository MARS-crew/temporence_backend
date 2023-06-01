package mars.ourmindmaze.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.dto.item.RequestItemSaveDto;
import mars.ourmindmaze.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @PostMapping
    private ResponseEntity<?> saveItem(@RequestBody RequestItemSaveDto dto) {
        return itemService.saveItem(dto);
    }

    @PatchMapping("/update/pointType/{id}")
    private ResponseEntity<?> updatePointType(@RequestBody RequestItemSaveDto dto, @PathVariable Long id) {
        return itemService.updatePointType(dto, id);
    }

    @PatchMapping("/update/itemType/{id}")
    private ResponseEntity<?> updateitemType(@RequestBody RequestItemSaveDto dto, @PathVariable Long id) {
        return itemService.updateItemType(dto, id);
    }

    @PatchMapping("/update/cost/{id}")
    private ResponseEntity<?> updateItemCost(@RequestBody RequestItemSaveDto dto, @PathVariable Long id) {
        return itemService.updateItemCost(dto, id);
    }

    @PatchMapping("/update/item/{id}")
    private ResponseEntity<?> updateItem(@RequestBody RequestItemSaveDto dto, @PathVariable Long id) {
        return itemService.updateItem(dto, id);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteItem(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @Operation(summary = "Find Item", description = "아이템 조회")
    @ApiResponse(responseCode = "400", description = "Parameter type is incorrect")
    @ApiResponse(responseCode = "401", description = "Bad Credentials, JWT token expires")
    @ApiResponse(responseCode = "401", description = "Access denied")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/{id}")
    public ResponseEntity<?> findItemById(@PathVariable(name = "id") Long id) {
        return itemService.findItemById(id);
    }
}
