package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.TeamType;

import javax.persistence.*;

@Entity
@Table(name = "tbl_character")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private TeamType teamType;
}
