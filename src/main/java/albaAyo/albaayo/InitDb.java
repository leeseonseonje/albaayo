package albaAyo.albaayo;

import albaAyo.albaayo.domains.chat.domain.PersonalChat;
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
            Member employerA = Member.builder()
                    .userId("employer")
                    .password(pw)
                    .email("memberA@gmail.com")
                    .name("employerA")
                    .birth("1998년 3월 17일")
                    .role(Role.ROLE_EMPLOYER)
                    .build();

            Member workerA = Member.builder()
                    .userId("worker")
                    .password(pw)
                    .email("mecxzbmberB@g.com")
                    .name("workerA")
                    .birth("1998년 2월 9일")
                    .role(Role.ROLE_WORKER)
                    .build();
//
//            Member workerB = Member.builder()
//                    .userId("workdasdasdaser")
//                    .password(pw)
//                    .email("memberdasdasB@g.com")
//                    .name("workerB")
//                    .birth("1998년 2월 9일")
//                    .role(Role.ROLE_WORKER)
//                    .build();
//
//            Member workerC = Member.builder()
//                    .userId("workedasr")
//                    .password(pw)
//                    .email("mdasemberB@g.com")
//                    .name("workerC")
//                    .birth("1998년 2월 9일")
//                    .role(Role.ROLE_WORKER)
//                    .build();
//
//            Member workerD = Member.builder()
//                    .userId("wordasker")
//                    .password(pw)
//                    .email("membdaserB@g.com")
//                    .name("workerD")
//                    .birth("1998년 2월 9일")
//                    .role(Role.ROLE_WORKER)
//                    .build();

            RefreshToken A = RefreshToken.builder().id("1").token("222").build();
            RefreshToken B = RefreshToken.builder().id("2").token("2222").build();
            em.persist(A);
            em.persist(B);
            em.persist(employerA);
            em.persist(workerA);
//            em.persist(workerB);
//            em.persist(workerC);
//            em.persist(workerD);

//            Company companyA = Company.builder()
//                    .name("companyA")
//                    .location("das")
//                    .businessRegistrationNumber("1234567890")
//                    .build();
//
//            Company companyB = Company.builder()
//                    .name("companyB")
//                    .location("da")
//                    .businessRegistrationNumber("123478")
//                    .build();
//
//            Company companyC = Company.builder()
//                    .name("companyC")
//                    .location("d")
//                    .businessRegistrationNumber("12367890")
//                    .build();
//            Company companyD = Company.builder()
//                    .name("companyD")
//                    .location("dadass")
//                    .businessRegistrationNumber("190")
//                    .build();
//
//            Company companyE = Company.builder()
//                    .name("companyE")
//                    .location("dasdassdadsa")
//                    .businessRegistrationNumber("1")
//                    .build();

//            em.persist(companyA);
//            companyA.employerCreateCompany(employerA);
//            em.persist(companyB);
//            companyB.employerCreateCompany(employerA);
//            em.persist(companyC);
//            companyC.employerCreateCompany(employerA);
//            em.persist(companyD);
////            companyD.employerCreateCompany(employerA);
//            em.persist(companyE);
////            companyE.employerCreateCompany(employerA);
//
////            JoinCompany jA = JoinCompany.builder()
////                    .accept(Accept.ACCEPT)
////                    .company(companyA)
////                    .member(workerA)
////                    .build();
//            JoinCompany jB = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyB)
//                    .member(workerA)
//                    .build();
//            JoinCompany jB1 = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyB)
//                    .member(workerB)
//                    .build();
//            JoinCompany jB2 = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyB)
//                    .member(workerC)
//                    .build();
//            JoinCompany jB3 = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyB)
//                    .member(workerD)
//                    .build();
//            JoinCompany C = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyC)
//                    .member(workerA)
//                    .build();
//            JoinCompany D = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyD)
//                    .member(workerA)
//                    .build();
//            JoinCompany E = JoinCompany.builder()
//                    .accept(Accept.ACCEPT)
//                    .company(companyE)
//                    .member(workerA)
//                    .build();
////            em.persist(jA);
//            em.persist(jB);
//            em.persist(jB1);
//            em.persist(jB2);
//            em.persist(jB3);
//            em.persist(C);
//            em.persist(D);
//            em.persist(E);

//            PersonalChat pchA1 = PersonalChat.builder().chatContent("pchA1").sendMember(employerA).recvMember(workerA).build();
//            PersonalChat pchA2 = PersonalChat.builder().chatContent("pchA2").sendMember(employerA).recvMember(workerA).build();
//            PersonalChat pchA3 = PersonalChat.builder().chatContent("pchA3").sendMember(employerA).recvMember(workerA).build();
//
//            PersonalChat pchB1 = PersonalChat.builder().chatContent("pchB1").sendMember(employerA).recvMember(workerB).build();
//            PersonalChat pchB2 = PersonalChat.builder().chatContent("pchB2").sendMember(employerA).recvMember(workerB).build();
//
//            PersonalChat pchC1 = PersonalChat.builder().chatContent("pchC1").sendMember(employerA).recvMember(workerC).build();
//            PersonalChat pchC2 = PersonalChat.builder().chatContent("pchC2").sendMember(employerA).recvMember(workerC).build();
//            PersonalChat pchC3 = PersonalChat.builder().chatContent("pchC3").sendMember(employerA).recvMember(workerC).build();
//            PersonalChat pchC4 = PersonalChat.builder().chatContent("pchC4").sendMember(employerA).recvMember(workerC).build();
//            PersonalChat pchC5 = PersonalChat.builder().chatContent("pchC5").sendMember(employerA).recvMember(workerC).build();
//
//            em.persist(pchA1);
//            em.persist(pchA2);
//            em.persist(pchA3);
//
//            em.persist(pchB1);
//            em.persist(pchB2);
//
//            em.persist(pchC1);
//            em.persist(pchC2);
//            em.persist(pchC3);
//            em.persist(pchC4);
//            em.persist(pchC5);
        }
    }
}
