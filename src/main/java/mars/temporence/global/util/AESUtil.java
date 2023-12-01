package mars.temporence.global.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.spec.AlgorithmParameterSpec;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.aspectj.util.LangUtil.isEmpty;

public class AESUtil {
    private static final String EMPTY_TEXT = "";
    public static String encrypt(String key, String ivKey, String str) throws GeneralSecurityException {
        if(isEmpty(str)) return EMPTY_TEXT;
        if(isEmpty(key)) throw new InvalidKeyException();

        byte[] textBytes = str.getBytes(UTF_8);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivKey.getBytes(UTF_8));
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

        return new String(Base64.encodeBase64(cipher.doFinal(textBytes)));
    }

    public static String decrypt(String key, String ivKey, String str) throws GeneralSecurityException {
        if(isEmpty(str)) return EMPTY_TEXT;
        if(isEmpty(key)) throw new InvalidKeyException();

        byte[] textBytes = Base64.decodeBase64(str.getBytes(UTF_8));

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivKey.getBytes(UTF_8));
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);

        return new String(cipher.doFinal(textBytes), UTF_8);
    }
}
