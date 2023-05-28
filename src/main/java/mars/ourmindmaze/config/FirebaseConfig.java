package mars.ourmindmaze.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {


    private final FirebaseApp firebaseApp;

    public FirebaseConfig() {
        firebaseApp = FirebaseApp.getApps().isEmpty() ? initializeFirebaseApp() : FirebaseApp.getInstance();
    }

    private FirebaseApp initializeFirebaseApp() {
        try {
            InputStream serviceAccount = getClass().getResourceAsStream("/firebase/mars-temporence-firebase-adminsdk-aq7jc-2fef664008.json");

            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize FirebaseApp.", e);
        }
    }

    @Bean
    public FirebaseApp firebaseApp() {
        return firebaseApp;
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}