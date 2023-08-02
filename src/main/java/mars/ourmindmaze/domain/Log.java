package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.TeamType;

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
    private TeamType winner;

    @Column(nullable = false)
    private int playTime;

}
