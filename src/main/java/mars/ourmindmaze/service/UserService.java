package mars.ourmindmaze.service;

import mars.ourmindmaze.common.dto.RequestResponseDto;
import mars.ourmindmaze.dto.RequestLoginUserDto;
import mars.ourmindmaze.dto.RequestSaveUserDto;
import mars.ourmindmaze.dto.RequestTokenDto;

public interface UserService {
    RequestResponseDto<?> save(RequestSaveUserDto dto);

    RequestResponseDto<?> login(RequestLoginUserDto dto);

    RequestResponseDto<?> getTokenByRefreshToken(RequestTokenDto dto);
}
