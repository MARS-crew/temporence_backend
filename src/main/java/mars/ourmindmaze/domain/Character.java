package mars.ourmindmaze.domain;

import lombok.*;
import mars.ourmindmaze.common.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skin> skins;
}
