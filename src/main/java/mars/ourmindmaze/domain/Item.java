package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.ItemType;
import mars.ourmindmaze.enums.PointType;

import javax.persistence.*;

@Entity
@Table(name = "tbl_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "itemType")
    private ItemType itemType;

    @Column(name = "pointType")
    private PointType pointType;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "item", length = 50)
    private String item;

}
