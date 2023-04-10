package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mars.ourmindmaze.common.dto.UserAuthority;
import mars.ourmindmaze.common.entity.BaseTimeEntity;
import mars.ourmindmaze.enums.UserType;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name ="user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", nullable = true)
    private String password;

    @Column(name = "user_name", nullable = true)
    private String name;

    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private UserAuthority authority;
}
