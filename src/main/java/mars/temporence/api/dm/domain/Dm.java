package mars.temporence.api.dm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.temporence.global.domain.BaseTimeEntity;
import mars.temporence.api.user.domain.User;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "tbl_dm")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dm extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_send_id", referencedColumnName = "id")
    @Comment("보내는 자")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_recive_id", referencedColumnName = "id")
    @Comment("받는 자")
    private User reciver;

    @Column(name = "content", nullable = false, length = 120)
    @Comment("내용")
    private String content;
}
