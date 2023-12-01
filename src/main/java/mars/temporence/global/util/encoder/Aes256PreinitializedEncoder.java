package mars.temporence.global.util.encoder;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

@Component
public class Aes256PreinitializedEncoder {

    public byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    @Value("${encrypt-util.secret}")
    private String key;

    public Aes256PreinitializedEncoder()  {
    }

    public String AES_Encode(String str) throws Exception {
        Cipher encodeCipher = createCipher(Cipher.ENCRYPT_MODE);

        byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeBase64URLSafeString(encodeCipher.doFinal(textBytes));
    }

    public String AES_Decode(String str) throws Exception {
        Cipher decodeCipher = createCipher(Cipher.DECRYPT_MODE);

        byte[] textBytes = Base64.decodeBase64(str.getBytes());
        return new String(decodeCipher.doFinal(textBytes), StandardCharsets.UTF_8);
    }

    private Cipher createCipher(final int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, newKey, ivSpec);
        return cipher;
    }

}
