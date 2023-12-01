package mars.temporence.api.userSkin.domain;

import lombok.*;
import mars.temporence.api.skin.domain.Skin;
import mars.temporence.api.user.domain.User;
import mars.temporence.global.domain.BaseTimeEntity;

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
