package mars.temporence.dm;

import mars.temporence.common.dto.UserAuthority;
import mars.temporence.domain.Dm;
import mars.temporence.domain.User;
import mars.temporence.repository.dm.DmJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DmServiceTestV0 {

    @Autowired
    private DmJpaRepository dmJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @DisplayName("dm 전송 테스트 - SUCCESS")
    @Test
    @Transactional
    @Rollback
    void sendDm() {
        // given
        User sender = userJpaRepository.save(User.builder()
                .username("sender")
                .password("1234")
                .nickname("sender")
                .authority(UserAuthority.ROLE_USER)
                .build());

        User reciver = userJpaRepository.save(User.builder()
                .username("reciver")
                .password("1234")
                .nickname("reciver")
                .authority(UserAuthority.ROLE_USER)
                .build());

        Dm sendDm = dmJpaRepository.save(Dm.builder()
                .sender(sender)
                .reciver(reciver)
                .content("Hello")
                .build());

        // when
        Optional<Dm> findDm = dmJpaRepository.findById(sendDm.getId());

        // then
        Assertions.assertThat(sendDm).isEqualTo(findDm.get());
    }

    @DisplayName("dm 조회 테스트 - SUCCESS")
    @Test
    @Transactional
    @Rollback
    void findDmTest() {
        // given
        User user = userJpaRepository.save(User.builder()
                .username("user")
                .password("1234")
                .nickname("user")
                .authority(UserAuthority.ROLE_USER)
                .build());

        User friend = userJpaRepository.save(User.builder()
                .username("friend")
                .password("1234")
                .nickname("friend")
                .authority(UserAuthority.ROLE_USER)
                .build());

        List<Dm> dm = new ArrayList<>();

        dm.add(dmJpaRepository.save(Dm.builder()
                .sender(user)
                .reciver(friend)
                .content("Hello")
                .build()));

        dm.add(dmJpaRepository.save(Dm.builder()
                .sender(friend)
                .reciver(user)
                .content("Hello")
                .build()));

        // when
        List<Dm> findDm = dmJpaRepository.findDms(user, friend);

        // then
        Assertions.assertThat(findDm).isEqualTo(dm);
    }
}
