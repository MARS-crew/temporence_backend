package mars.temporence.service.impl;

import lombok.RequiredArgsConstructor;
import mars.temporence.common.dto.ApiResponse;
import mars.temporence.common.dto.CommonResponse;
import mars.temporence.domain.Character;
import mars.temporence.domain.Log;
import mars.temporence.domain.Player;
import mars.temporence.domain.User;
import mars.temporence.dto.playerLog.RequestLogSaveDto;
import mars.temporence.dto.playerLog.RequestPlayerSaveDto;
import mars.temporence.repository.CharacterJpaRepository;
import mars.temporence.repository.playerLog.LogJpaRepository;
import mars.temporence.repository.playerLog.PlayerJpaRepository;
import mars.temporence.repository.user.UserJpaRepository;
import mars.temporence.service.LogService;
import mars.temporence.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogJpaRepository logJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CharacterJpaRepository characterJpaRepository;

    @Override
    public ResponseEntity<?> saveLog(RequestLogSaveDto dto) {
        Log log = logJpaRepository.save(Log.builder().winner(dto.getWinner()).playTime(dto.getPlayTime()).build());

        for (RequestPlayerSaveDto player: dto.getPlayers()) {
            Optional<User> findUser = userJpaRepository.findByNickname(player.getNickname());

            if (findUser.isEmpty()) {
                return ApiResponse.<Object>builder().status(HttpStatus.NOT_FOUND).message("해당 유저를 찾을 수 없습니다. " + player.getNickname()).buildObject();
            }

            Optional<Character> findCharacter = characterJpaRepository.findByName(player.getCharacterNickname());

            if (findCharacter.isEmpty()) {
                return ApiResponse.<Objects>builder().status(HttpStatus.NOT_FOUND).message("캐릭터를 찾을 수 없습니다.").buildObject();
            }

            playerJpaRepository.save(Player.builder().log(log).user(findUser.get()).character(findCharacter.get()).prisonCount(player.getPrisonCount()).role(player.getRole()).build());
        }

        return CommonResponse.createResponseMessage(HttpStatus.CREATED.value(), "게임 기록에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> findLogList() {

        User loginUser = SecurityUtil.getCurrentUserId(userJpaRepository);
        List<Player> playList = playerJpaRepository.findPlayersByUser(loginUser, LocalDateTime.now().minusMonths(3));

        List<List<Player>> result = new ArrayList<>();

        for(Player p: playList) {
            List<Player> players = playerJpaRepository.findPlayersByLog_Id(p.getLog().getId());
            result.add(players);
        }

        return CommonResponse.createResponse(HttpStatus.OK.value(), "게임 기록 조회에 성공했습니다.", result);
    }
}
