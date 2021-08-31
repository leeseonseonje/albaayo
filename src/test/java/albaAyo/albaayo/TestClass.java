package albaAyo.albaayo;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.service.MemberService;
import albaAyo.albaayo.notice.domain.Notice;
import albaAyo.albaayo.notice.domain.NoticeImage;
import albaAyo.albaayo.notice.dto.NoticeImageDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeDto;
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
import java.util.List;
import java.util.stream.Collectors;

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
        Notice notice = noticeRepository.findById(48L).get();

//        System.out.println("notice = " + notice.getMember().getName());
//        System.out.println("notice.getCompany().getName() = " + notice.getCompany().getName());
//        System.out.println("notice.getNoticeImages().size() = " + notice.getNoticeImages().size());
//        System.out.println("notice.getNoticeImages().size() = " + notice.getNoticeImages().get(0));
//        System.out.println("notice.getNoticeImages().size() = " + notice.getNoticeImages().get(1));

        List<NoticeImageDto> collect = notice.getNoticeImages().stream()
                .map(m -> new NoticeImageDto(m.getImage(), m.getImageContent()))
                .collect(Collectors.toList());

        ResponseNoticeDto build = ResponseNoticeDto.builder().noticeId(notice.getId())
                .memberId(notice.getMember().getId())
                .name(notice.getMember().getName())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .date(notice.getDate())
                .imageList(collect).build();

        System.out.println("build.getNoticeId() = " + build.getNoticeId());
        System.out.println("build.getNoticeId() = " + build.getMemberId());
        System.out.println("build.getNoticeId() = " + build.getName());
        System.out.println("build.getNoticeId() = " + build.getTitle());
        System.out.println("build.getNoticeId() = " + build.getContents());
        System.out.println("build.getNoticeId() = " + build.getDate());
        System.out.println("build.getNoticeId() = " + build.getImageList().size());
    }
}
