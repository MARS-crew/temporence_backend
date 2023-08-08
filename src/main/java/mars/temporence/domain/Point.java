package mars.temporence.domain;

import lombok.*;
import mars.temporence.common.entity.BaseTimeEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "tbl_point")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "point_gold", nullable = false)
    @Comment("골드 포인트")
    private Integer gold;

    @Column(name = "point_blue", nullable = false)
    @Comment("블루 포인트")
    private Integer blue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("유저")
    private User user;
}
