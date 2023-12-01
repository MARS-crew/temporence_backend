package mars.temporence.api.user.service;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.dto.CommonResponse;
import mars.temporence.global.dto.Pagination;
import mars.temporence.api.point.repository.PointJpaRepository;
import mars.temporence.api.user.repository.UserJpaRepository;
import mars.temporence.api.user.event.vo.UserVO;
import mars.temporence.global.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final PointJpaRepository pointJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public ResponseEntity<?> findAll(Pageable pageable) throws Exception {
        Page<UserVO> response = userJpaRepository.findAllUser(pageable);
        List<UserVO> data = response.getContent();
        Pagination pagination = Pagination.builder().totalPages(response.getTotalPages()).currentPage(response.getNumber()).totalItems(response.getTotalElements()).build();

        return CommonResponse.createResponseWithPagination(HttpStatus.OK.value(), "유저 리스트를 조회 합니다.", data, pagination);
    }
}
