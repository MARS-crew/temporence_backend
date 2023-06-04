package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.SocialType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name ="username", unique = true, nullable = false, length = 25)
    private String username;

    @Column(name = "password", nullable = true, length = 120)
    private String password;

    @Column(nullable = false)
    private SocialType socialType;

    @Column(unique = true, nullable = true, length = 120)
    private String socialKey;

    @Column(nullable = false)
    private UserAuthority authority;
}
