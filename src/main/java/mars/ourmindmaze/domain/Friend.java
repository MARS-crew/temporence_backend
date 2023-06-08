package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_friend")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friend;
}
