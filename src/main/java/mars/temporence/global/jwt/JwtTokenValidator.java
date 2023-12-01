package mars.temporence.global.jwt;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars.temporence.global.exception.UnAuthenticationException;
import org.springframework.stereotype.Component;

import java.security.PublicKey;


/**
 * JWT 토큰 검증 클래스
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final JwtConfig jwtConfig;

    /**
     * 토큰 인증
     */
    public void validateToken(String token) throws UnAuthenticationException {
        final PublicKey key = jwtConfig.getPublicKey();
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new UnAuthenticationException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new UnAuthenticationException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new UnAuthenticationException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new UnAuthenticationException("JWT 토큰이 잘못되었습니다.");
        }
    }

    /**
     * Get claims from Token
     */

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtConfig.getPublicKey()).build().parseClaimsJws(token).getBody();
    }
}
