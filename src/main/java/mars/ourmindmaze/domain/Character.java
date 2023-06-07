package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_character")
@Data
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
