package albaAyo.albaayo.config.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmService {

    @Value("${fcm.path}")
    private String path;

    @PostConstruct
    public void initFirebase() {

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.info("error={}",e.getMessage());
        }
    }

    public void sendMessage(String token, String title, String body) throws ExecutionException, InterruptedException {
        Message message = makeMessage(token, title, body);
        FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message makeMessage(String token, String title, String body) {
        return Message.builder()
                .setToken(token)
                .setNotification(new Notification(title, body))
                .build();
    }

    public void sendMulticastMessage(List<String> fcmTokens, String title, String body) throws ExecutionException, InterruptedException {
        MulticastMessage multicastMessage = makeMulticastMessage(fcmTokens, title, body);
        FirebaseMessaging.getInstance().sendMulticastAsync(multicastMessage).get();
    }

    private MulticastMessage makeMulticastMessage(List<String> fcmTokens, String title, String body) {
        return MulticastMessage.builder()
                .addAllTokens(fcmTokens)
                .setNotification(new Notification(title, body))
                .build();
    }

}
