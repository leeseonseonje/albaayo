package albaAyo.albaayo;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import albaAyo.albaayo.domains.member.service.MemberService;
import albaAyo.albaayo.domains.notice.domain.Notice;
import albaAyo.albaayo.domains.notice.dto.NoticeImageDto;
import albaAyo.albaayo.domains.notice.dto.ResponseNoticeDto;
import albaAyo.albaayo.domains.notice.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
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
import java.util.ArrayList;
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
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    JoinCompanyRepository joinCompanyRepository;

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

    @Test
    public void nullTest() {
        Member member = memberRepository.findByFcmToken("dsa");
        System.out.println("dsad = " + member);
        Assertions.assertThat(member).isNull();
    }

    @Test
    public void nullTest2() {
        Member member = memberRepository.findById(1L).get();
        member.fcmTokenSetting("ddddd");
        System.out.println(member.getFcmToken());
        Assertions.assertThat(member.getFcmToken()).isEqualTo("ddddd");
        member.fcmTokenSetting(null);
        System.out.println(member.getFcmToken());
        Assertions.assertThat(member.getFcmToken()).isNull();
    }

    @Test
    public void typeCheck() {
        String s = "string";
        Integer i = 1;
        int x = 0;
        Long id = 1L;
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("s");
        list.add("s");
        list.add("s");

        System.out.println(s.getClass().getName());
        System.out.println(i.getClass().getName());
        System.out.println(id.getClass().getName());
        System.out.println(list.getClass().getName());
    }

    @Test
    public void joinTest() {
        Company companyA = Company.builder()
                .name("companyA")
                .location("das")
                .businessRegistrationNumber("1234567890")
                .build();

        Company companyB = Company.builder()
                .name("companyB")
                .location("da")
                .businessRegistrationNumber("123478")
                .build();

        Company companyC = Company.builder()
                .name("companyC")
                .location("d")
                .businessRegistrationNumber("12367890")
                .build();
        Company companyD = Company.builder()
                .name("companyD")
                .location("dadass")
                .businessRegistrationNumber("190")
                .build();

        Company companyE = Company.builder()
                .name("companyE")
                .location("dasdassdadsa")
                .businessRegistrationNumber("1")
                .build();

        companyRepository.save(companyA);
        companyRepository.save(companyB);
        companyRepository.save(companyC);
        companyRepository.save(companyD);
        companyRepository.save(companyE);

        Member member = memberRepository.findById(2L).get();
        JoinCompany A = JoinCompany.builder()
                .accept(Accept.ACCEPT)
                .company(companyA)
                .member(member)
                .build();
        JoinCompany B = JoinCompany.builder()
                .accept(Accept.ACCEPT)
                .company(companyB)
                .member(member)
                .build();
        JoinCompany C = JoinCompany.builder()
                .accept(Accept.ACCEPT)
                .company(companyC)
                .member(member)
                .build();
        JoinCompany D = JoinCompany.builder()
                .accept(Accept.ACCEPT)
                .company(companyD)
                .member(member)
                .build();
        JoinCompany E = JoinCompany.builder()
                .accept(Accept.ACCEPT)
                .company(companyE)
                .member(member)
                .build();
        joinCompanyRepository.save(A);
        joinCompanyRepository.save(B);
        joinCompanyRepository.save(C);
        joinCompanyRepository.save(D);
        joinCompanyRepository.save(E);

        System.out.println("==================================================================================");
        List<Company> companies = joinCompanyRepository.acceptCompanyList(member.getId(), Accept.ACCEPT);

        for (Company company : companies) {
            System.out.println("company = " + company.getName());
        }

    }
}
