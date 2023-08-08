package mars.temporence.user;

import mars.temporence.common.dto.UserAuthority;
import mars.temporence.domain.User;
import mars.temporence.repository.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;


@SpringBootTest
public class UserServiceTestV0 {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원가입 테스트 - SUCCESS")
    @Test
    @Transactional
    @Rollback
    void 회원가입() {
        // given
        User saveUser = userJpaRepository.save(User.builder()
                .username("testUserSave")
                .password("testPwd")
                .authority(UserAuthority.ROLE_USER)
                .build());

        // when
        Optional<User> findUser = userJpaRepository.findByUsername("testUserSave");

        // then
        Assertions.assertThat(saveUser).isEqualTo(findUser.get());
    }

//    @DisplayName("회원가입 테스트 - FAIL(중복 닉네임)")
//    @Test
//    @Transactional
//    @Rollback
//    void 회원가입실패중복닉네임() {
//        // given
//        userJpaRepository.save(User.builder()
//                .username("testUserSave")
//                .password("testPwd")
//                .authority(UserAuthority.ROLE_USER)
//                .build());
//
//        // when
//        Optional<User> findUser = userJpaRepository.findByUsername("testUserSave");
//
//        // then
//        Assertions.assertThat(!findUser.isEmpty()).isTrue();
//    }
//
//    @DisplayName("로그인 테스트 - SUCCESS")
//    @Test
//    void 로그인() {
//        // given
//        Optional<User> findUser = userJpaRepository.findByUsername("admin");
//        // when
//        boolean result =  passwordEncoder.matches("1234", findUser.get().getPassword());
//
//        // then
//        Assertions.assertThat(result).isTrue();
//    }
}