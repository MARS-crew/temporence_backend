package mars.temporence.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(
                new JwtAuthenticationFilter(tokenProvider, stringRedisTemplate),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}