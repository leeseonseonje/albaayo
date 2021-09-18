package albaAyo.albaayo;

import albaAyo.albaayo.domains.commute.Commute;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.config.jwt.RefreshToken;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        @Value("${admin.pw}")
        private String pw;

        public void dbInit1() {
            Member employerA = Member.builder().build().builder()
                    .userId("employer")
                    .password(pw)
                    .email("memberA@gmail.com")
                    .name("memberA")
                    .birth("1998년 3월 17일")
                    .role(Role.ROLE_EMPLOYER)
                    .build();

            Member workerA = Member.builder()
                    .userId("worker")
                    .password(pw)
                    .email("memberB@g.com")
                    .name("memberB")
                    .birth("1998년 2월 9일")
                    .role(Role.ROLE_WORKER)
                    .build();

            RefreshToken A = RefreshToken.builder().id("1").token("222").build();
            RefreshToken B = RefreshToken.builder().id("2").token("2222").build();
            em.persist(A);
            em.persist(B);
            em.persist(employerA);
            em.persist(workerA);

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

            em.persist(companyA);
            em.persist(companyB);
            em.persist(companyC);
            em.persist(companyD);
            em.persist(companyE);

            JoinCompany jA = JoinCompany.builder()
                    .accept(Accept.ACCEPT)
                    .company(companyA)
                    .member(workerA)
                    .build();
            JoinCompany jB = JoinCompany.builder()
                    .accept(Accept.ACCEPT)
                    .company(companyB)
                    .member(workerA)
                    .build();
            JoinCompany C = JoinCompany.builder()
                    .accept(Accept.ACCEPT)
                    .company(companyC)
                    .member(workerA)
                    .build();
            JoinCompany D = JoinCompany.builder()
                    .accept(Accept.ACCEPT)
                    .company(companyD)
                    .member(workerA)
                    .build();
            JoinCompany E = JoinCompany.builder()
                    .accept(Accept.ACCEPT)
                    .company(companyE)
                    .member(workerA)
                    .build();
            em.persist(jA);
            em.persist(jB);
            em.persist(C);
            em.persist(D);
            em.persist(E);
        }
    }
}
