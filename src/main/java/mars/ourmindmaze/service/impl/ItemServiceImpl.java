package mars.ourmindmaze.service.impl;

import lombok.RequiredArgsConstructor;
import mars.ourmindmaze.common.dto.ApiResponse;
import mars.ourmindmaze.common.dto.CommonResponse;
import mars.ourmindmaze.domain.Item;
import mars.ourmindmaze.dto.item.RequestItemSaveDto;
import mars.ourmindmaze.enums.ItemType;
import mars.ourmindmaze.enums.PointType;
import mars.ourmindmaze.repository.item.ItemJpaRepository;
import mars.ourmindmaze.service.ItemService;
import mars.ourmindmaze.vo.ItemVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemJpaRepository itemJpaRepository;

    @Override
    public ResponseEntity<?> saveItem(RequestItemSaveDto dto) {
        Optional<Item> findItem = itemJpaRepository.findByItem(dto.getItem());
        if(findItem.isPresent())
            return ApiResponse.<Object>builder().status(HttpStatus.BAD_REQUEST).message("존재하는 아이템 입니다.").buildObject();
        itemJpaRepository.save(Item.builder()
                .item(dto.getItem())
                .itemType(dto.getItemType())
                .pointType(dto.getPointType())
                .cost(dto.getCost())
                .build());
        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "상점에 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> updateItemType(RequestItemSaveDto dto, Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        Item item = findItem.get();

        if(dto.getItemType() == ItemType.ITEM || dto.getItemType() == ItemType.SKIN)
            item.setItemType(dto.getItemType());

        itemJpaRepository.save(item);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "아이템 타입 변경에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> updatePointType(RequestItemSaveDto dto, Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        Item item = findItem.get();

        if(dto.getPointType() == PointType.BLUE || dto.getPointType() == PointType.GOLD)
            item.setPointType(dto.getPointType());

        itemJpaRepository.save(item);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "포인트 타입 변경에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> updateItem(RequestItemSaveDto dto, Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        Item item = findItem.get();
        item.setItem(dto.getItem());

        itemJpaRepository.save(item);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "아이템 변경에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> updateItemCost(RequestItemSaveDto dto, Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        Item item = findItem.get();
        item.setCost(dto.getCost());

        itemJpaRepository.save(item);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "아이템 가격 변경에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()) {
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        itemJpaRepository.delete(findItem.get());
        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "아이템 삭제에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> findItemById(Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        if(findItem.isEmpty()){
            return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("아이템을 찾을 수 없습니다.").buildObject();
        }
        return CommonResponse.createResponse(HttpStatus.OK.value(), "아이템을 조회 합니다.", findItem.get());
    }

    @Override
    public ResponseEntity<?> findItemList() {
        List<ItemVO> list = itemJpaRepository.findItemList();

        return CommonResponse.createResponse(HttpStatus.OK.value(), "아이템 리스트를 조회합니다.", list);
    }
}
