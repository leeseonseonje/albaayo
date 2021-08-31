package albaAyo.albaayo;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.service.MemberService;
import albaAyo.albaayo.notice.domain.Notice;
import albaAyo.albaayo.notice.domain.NoticeImage;
import albaAyo.albaayo.notice.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class TestClass {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    MemberService memberService;
    @Autowired
    NoticeRepository noticeRepository;

    @BeforeEach
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void test() {
        Member member = em.find(Member.class, 1L);
        System.out.println("member.getPassword() = " + member.getPassword());
        String s = "admin";
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUserId(), "1234");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("authentication = " + authentication.getName());
    }

    @Test
    public void logoutTest() {
        memberService.logout(1L);
    }

    @Test
    public void noticeTest() {
        Notice notice = noticeRepository.findById(8L).get();
        System.out.println("notice.getTitle() = " + notice.getTitle());

        for (NoticeImage noticeImage : notice.getNoticeImages()) {
            int x = 0;
        }
    }
}
