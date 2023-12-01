package mars.temporence.global.util;

import lombok.RequiredArgsConstructor;
import mars.temporence.global.util.encoder.Aes256PreinitializedEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
@RequiredArgsConstructor
public class EncryptionUtils {

    private final Aes256PreinitializedEncoder aes256Encoder;


    public String encrypt(String text) {
        try {
            return aes256Encoder.AES_Encode(text);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String text)  {
        try {
            return aes256Encoder.AES_Decode(text);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
