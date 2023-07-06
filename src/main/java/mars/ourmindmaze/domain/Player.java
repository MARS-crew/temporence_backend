package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.enums.RoleType;

import javax.persistence.*;

@Entity
@Table(name = "tbl_player")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id")
    private Log log;

    @Column(name = "win", length = 1)
    private boolean win;

    @Column(name = "role")
    private RoleType role;

    @Column(name = "prisonCount")
    private int prisonCount;

    @Column(name = "playTime")
    private int playtime;


}
