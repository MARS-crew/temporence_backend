package mars.temporence.api.friend.domain;

import lombok.*;
import mars.temporence.global.common.entity.BaseTimeEntity;
import mars.temporence.api.user.domain.User;
import org.hibernate.annotations.Comment;

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
    private Long id;

    @Column(columnDefinition = "CHAR(1)", nullable = false)
    @Comment("친구 상태")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("유저")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    @Comment("친구")
    private User friend;
}
