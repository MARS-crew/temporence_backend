package mars.temporence.global.jwt;



import lombok.extern.slf4j.Slf4j;
import mars.temporence.global.enums.ErrorCode;
import mars.temporence.global.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Configuration
public class JwtConfig {

    @Value("${jwt.public-key}")
    private String publicKeyString;

    @Value("${jwt.private-key}")
    private String privateKeyString;

    public PublicKey getPublicKey() {
        try {
            final byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", ""));
            ;
            final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            final KeyFactory rsaFactory = KeyFactory.getInstance("RSA");
            return rsaFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("[JwtConfig] getPublicKey Error {}", e.getMessage());
            throw new ServerException("Auth Fail. Contact to Admin", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public PrivateKey getPrivateKey() {
        try {
            final byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", ""));
            ;
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            final KeyFactory rsaFactory = KeyFactory.getInstance("RSA");
            return rsaFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("[JwtConfig] getPrivateKey Error {}", e.getMessage());
            throw new ServerException("Auth Fail. Contact to Admin", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
