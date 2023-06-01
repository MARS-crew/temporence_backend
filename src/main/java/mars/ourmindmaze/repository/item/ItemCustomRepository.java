package mars.ourmindmaze.repository.item;

import mars.ourmindmaze.vo.ItemVO;

import java.util.List;

public interface ItemCustomRepository {
    List<ItemVO> findItemList();
}
