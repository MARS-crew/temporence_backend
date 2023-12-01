package mars.temporence.api.character.domain;

import lombok.*;
import mars.temporence.global.domain.BaseTimeEntity;
import mars.temporence.global.enums.TeamType;
import org.hibernate.annotations.Comment;

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
    @Comment("캐릭터 이름")
    private String name;

    @Column(nullable = false)
    @Comment("팀 타입")
    private TeamType teamType;
}
