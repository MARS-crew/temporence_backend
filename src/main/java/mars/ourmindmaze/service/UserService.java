package mars.ourmindmaze.service;

import mars.ourmindmaze.common.dto.RequestResponseDto;
import mars.ourmindmaze.dto.user.RequestUserLoginDto;
import mars.ourmindmaze.dto.user.RequestUserSaveDto;
import mars.ourmindmaze.dto.user.RequestTokenDto;

public interface UserService {
    RequestResponseDto<?> save(RequestUserSaveDto dto);

    RequestResponseDto<?> login(RequestUserLoginDto dto);

    RequestResponseDto<?> findAll();

    RequestResponseDto<?> getTokenByRefreshToken(RequestTokenDto dto);
}
