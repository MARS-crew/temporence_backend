package mars.temporence.playerLog;

import mars.temporence.common.dto.UserAuthority;
import mars.temporence.domain.Character;
import mars.temporence.domain.Log;
import mars.temporence.domain.Player;
import mars.temporence.domain.User;
import mars.temporence.enums.TeamType;
import mars.temporence.repository.CharacterJpaRepository;
import mars.temporence.repository.playerLog.LogJpaRepository;
import mars.temporence.repository.playerLog.PlayerJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LogServiceTestV0 {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private LogJpaRepository logJpaRepository;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private CharacterJpaRepository characterJpaRepository;


    @DisplayName("log 생성 테스트 - SUCCESS")
    @Test
    @Transactional
    @Rollback
    void saveLog() {

        // given
        Log log = logJpaRepository.save(Log.builder().winner(TeamType.RUNNER).playTime(600).build());

        List<Player> playerList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        characterJpaRepository.save(Character.builder().name("runner").teamType(TeamType.RUNNER).build());
        characterJpaRepository.save(Character.builder().name("stopper").teamType(TeamType.STOPPER).build());

        for(int i = 1; i <= 4; i++) {
            userList.add(userJpaRepository.save(User.builder().username("runner"+i).nickname("runner"+i).authority(UserAuthority.ROLE_USER).password("1234").build()));
        }

        userList.add(userJpaRepository.save(User.builder().username("stopper").nickname("stopper").authority(UserAuthority.ROLE_USER).password("1234").build()));

        for(int i = 0; i < 4; i++) {
            playerList.add(playerJpaRepository.save(Player.builder().user(userList.get(i)).log(log).character(characterJpaRepository.findByName("runner").get()).prisonCount(1).role(TeamType.RUNNER).build()));
        }

        playerList.add(playerJpaRepository.save(Player.builder().user(userList.get(4)).log(log).character(characterJpaRepository.findByName("stopper").get()).prisonCount(4).role(TeamType.STOPPER).build()));

        // when
        List<Player> player_log = playerJpaRepository.findAll();

        // then
        Assertions.assertThat(player_log).isEqualTo(playerList);

    }

    @DisplayName("log 조회 테스트 - SUCCESS")
    @Test
    @Transactional
    @Rollback
    void findLog() {

        // given
        Log log = logJpaRepository.save(Log.builder().winner(TeamType.RUNNER).playTime(600).build());

        List<Player> playerList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        characterJpaRepository.save(Character.builder().name("runner").teamType(TeamType.RUNNER).build());
        characterJpaRepository.save(Character.builder().name("stopper").teamType(TeamType.STOPPER).build());

        for(int i = 1; i <= 4; i++) {
            userList.add(userJpaRepository.save(User.builder().username("runner"+i).nickname("runner"+i).authority(UserAuthority.ROLE_USER).password("1234").build()));
        }

        userList.add(userJpaRepository.save(User.builder().username("stopper").nickname("stopper").authority(UserAuthority.ROLE_USER).password("1234").build()));

        for(int i = 0; i < 4; i++) {
            playerList.add(playerJpaRepository.save(Player.builder().user(userList.get(i)).log(log).character(characterJpaRepository.findByName("runner").get()).prisonCount(1).role(TeamType.RUNNER).build()));
        }

        playerList.add(playerJpaRepository.save(Player.builder().user(userList.get(4)).log(log).character(characterJpaRepository.findByName("stopper").get()).prisonCount(4).role(TeamType.STOPPER).build()));

        // when
        List<Player> player_log = playerJpaRepository.findPlayersByUser(userList.get(0), LocalDateTime.now().minusMonths(3));

        List<List<Player>> result = new ArrayList<>();

        for(Player p: player_log) {
            result.add(playerJpaRepository.findPlayersByLog_Id(p.getLog().getId()));
        }

        // then
        Assertions.assertThat(result.get(0)).isEqualTo(playerList);

    }


}
