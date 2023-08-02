package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.TeamType;

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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id")
    private Log log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    @Column(name = "role")
    private TeamType role;

    @Column(name = "prisonCount")
    private int prisonCount;

}
