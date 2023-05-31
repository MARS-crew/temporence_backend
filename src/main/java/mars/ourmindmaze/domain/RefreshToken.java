package mars.ourmindmaze.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
@Data
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String id;
    private String username;
}
