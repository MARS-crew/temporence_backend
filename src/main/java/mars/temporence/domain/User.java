package mars.temporence.domain;

import lombok.*;
import mars.temporence.common.dto.UserAuthority;
import mars.temporence.common.entity.BaseTimeEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Comment("로그인 ID")
    private String username;

    @Column(unique = true, nullable = false, length = 50)
    @Comment("닉네임")
    private String nickname;

    @Column(length = 120, nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(nullable = false)
    @Comment("권한")
    private UserAuthority authority;
}
