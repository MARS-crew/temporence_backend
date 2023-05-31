package mars.ourmindmaze.dto.item;

import lombok.Data;
import mars.ourmindmaze.enums.ItemType;
import mars.ourmindmaze.enums.PointType;

@Data
public class RequestItemSaveDto {

    private ItemType itemType;
    private PointType pointType;
    private String item;
    private Integer cost;

}
