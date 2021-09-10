package albaAyo.albaayo.config.fcm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class FcmServiceTest {

    @Autowired
    private FcmService fcmService;

    @Value("${fcm.androidTestToken}")
    private String token;

    @Test
    public void fcmSendTest() throws Exception {
        fcmService.sendMessage(token, "회사", "동탁:안녕하세요");
        fcmService.sendMessage(token, "회사", "동탁: 안녕하세요");
        fcmService.sendMessage(token, "회사", "동탁 : 안녕하세요");
    }

    @Test
    public void fcmMulticastTest() throws ExecutionException, InterruptedException {
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(token);
        fcmService.sendMulticastMessage(list, "title", "body");
    }
}