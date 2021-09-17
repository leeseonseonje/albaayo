package albaAyo.albaayo.config.fcm;

import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
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
    FcmService fcmService;

    @Autowired
    MemberRepository memberRepository;


    @Value("${fcm.androidTestToken}")
    private String token;

    @Test
    public void fcmSendTest() throws Exception {
        fcmService.sendMessage(token, "회사", "동탁:안녕하세요");
        fcmService.sendMessage(token, "회사", "동탁: 안녕하세요");
        fcmService.sendMessage(token, "회사", "동탁 : 안녕하세요");
        fcmService.sendMessage("sdaasda", "회사", "동탁 : 안녕하세요");
    }

    @Test
    public void fcmMulticastTest() throws ExecutionException, InterruptedException {
        List<String> list = new ArrayList<>();
//        list.add(token);
//        list.add(token);
        fcmService.sendMulticastMessage(list, "title", "body");
    }

    @Test
    public void fcmNullTest() {
        Member memberA = memberRepository.findById(1L).get();
        Member memberB = memberRepository.findById(2L).get();

        List<String> list = new ArrayList<>();

        Assertions.assertThat(list).isEmpty();
    }
}