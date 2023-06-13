package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
@Getter
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String id;
    private String username;
}
