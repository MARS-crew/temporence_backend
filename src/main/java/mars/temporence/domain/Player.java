package mars.temporence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.temporence.common.entity.BaseTimeEntity;
import mars.temporence.enums.TeamType;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "tbl_player")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("유저")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id")
    private Log log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    @Column(name = "role")
    @Comment("팀 타입")
    private TeamType role;

    @Column(name = "prisonCount")
    @Comment("탈옥 회수(감금 횟수)")
    private int prisonCount;

}
