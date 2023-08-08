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
@Table(name = "tbl_gameLog")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Comment("승리 팀 타입")
    private TeamType winner;

    @Column(nullable = false)
    @Comment("플레이 시간")
    private int playTime;

}
