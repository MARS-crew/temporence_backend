package mars.temporence.domain;

import lombok.*;
import mars.temporence.common.entity.BaseTimeEntity;
import mars.temporence.enums.TeamType;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_skin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @Comment("스킨 이름")
    private String name;

    @Column(nullable = false)
    @Comment("팀 타입")
    private TeamType teamType;

    @OneToMany(mappedBy = "skin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSkin> userSkins;
}
