package mars.temporence.domain;

import lombok.*;
import mars.temporence.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_userSkin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSkin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skin_id")
    private Skin skin;
}
