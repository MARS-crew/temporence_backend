package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.TeamType;

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
    private String name;

    @Column(nullable = false)
    private TeamType teamType;

    @OneToMany(mappedBy = "skin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSkin> userSkins;
}
