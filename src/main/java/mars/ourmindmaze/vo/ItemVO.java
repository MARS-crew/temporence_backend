package mars.ourmindmaze.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import mars.ourmindmaze.enums.ItemType;
import mars.ourmindmaze.enums.PointType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ItemVO {
    private Long id;
    private ItemType itemType;
    private PointType pointType;
    private Integer cost;
    private String item;
    private LocalDateTime createdDate;
}
