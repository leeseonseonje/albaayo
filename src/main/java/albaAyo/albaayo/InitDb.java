//package albaAyo.albaayo;
//
//import albaAyo.albaayo.domains.commute.Commute;
//import albaAyo.albaayo.domains.company.domain.Company;
//import albaAyo.albaayo.config.jwt.RefreshToken;
//import albaAyo.albaayo.domains.member.domain.Member;
//import albaAyo.albaayo.domains.member.domain.Role;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//
//        public void dbInit1() {
//            Member employerA = Member.builder().build().builder()
//                    .userId("employer")
//                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
//                    .email("leeseonje@gmail.com")
//                    .name("정성권")
//                    .birth("1998년 3월 17일")
//                    .role(Role.ROLE_EMPLOYER)
//                    .build();
//
////            Member employerB = Member.builder()
////                    .userId("EMPLOYERB")
////                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
////                    .email("seon9323@naver.com")
////                    .name("employerB")
////                    .birth("1998.02.09")
////                    .role(Role.ROLE_EMPLOYER)
////                    .build();
//
//            Member workerA = Member.builder()
//                    .userId("worker")
//                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
//                    .email("seon9323@g.com")
//                    .name("이선제")
//                    .birth("1998년 2월 9일")
//                    .role(Role.ROLE_WORKER)
//                    .build();
//
////            Member workerB = Member.builder()
////                    .userId("workerB")
////                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
////                    .email("seon9323@gddddddd.com")
////                    .name("이선제")
////                    .birth("1998.02.09")
////                    .role(Role.ROLE_WORKER)
////                    .build();
////
////            Member workerC = Member.builder()
////                    .userId("workerC")
////                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
////                    .email("seon9323@dasgddddddd.com")
////                    .name("이선제")
////                    .birth("1998.02.09")
////                    .role(Role.ROLE_WORKER)
////                    .build();
//            RefreshToken A = RefreshToken.builder().id("1").token("222").build();
//            RefreshToken B = RefreshToken.builder().id("2").token("2222").build();
//            em.persist(A);
//            em.persist(B);
////
//            em.persist(employerA);
////            em.persist(employerB);
//            em.persist(workerA);
////            em.persist(workerB);
////            em.persist(workerC);
////            em.persist(reA);
////            em.persist(reB);
////
//            Company companyA = Company.builder()
//                    .name("영진전문대 복현캠퍼스")
//                    .location("대구광역시 달성군 다사읍 서재로120 107동 905호")
//                    .businessRegistrationNumber("1234567890")
//                    .build();
//////
//////
////            Company companyB = Company.builder()
////                    .name("B")
////                    .location("서울")
////                    .businessRegistrationNumber("0987654321")
////                    .build();
//////
////            Company companyC = Company.builder()
////                    .name("C")
////                    .location("미국")
////                    .businessRegistrationNumber("32134254365")
////                    .build();
////
////            Company companyD = Company.builder()
////                    .name("D")
////                    .location("서울")
////                    .businessRegistrationNumber("456548675")
////                    .build();
////
////            JoinCompany joinCompanyOne = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyTwo = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyThree = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyFour = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyFive = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanySix = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanySeven = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyEight = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyNine = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyTen = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyTenA = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyA)
////                    .accept(Accept.NOT_ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyTenB = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyB)
////                    .accept(Accept.NOT_ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyTenC = JoinCompany.builder()
////                    .member(workerA)
////                    .company(companyC)
////                    .accept(Accept.NOT_ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyB = JoinCompany.builder()
////                    .member(workerB)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
////            JoinCompany joinCompanyC = JoinCompany.builder()
////                    .member(workerC)
////                    .company(companyA)
////                    .accept(Accept.ACCEPT)
////                    .build();
////
//            Commute commute = Commute.builder().member(workerA).company(companyA).startTime(LocalDateTime.now()).build();
//
//            Commute commuteB = Commute.builder().member(workerA).company(companyA).startTime(LocalDateTime
//                    .of(2021,7,1,5,1))
//                    .EndTime(LocalDateTime.of(2021, 7, 1, 12, 0)).build();
//
//            Commute commuteC = Commute.builder().member(workerA).company(companyA).startTime(LocalDateTime
//                    .of(2021,7,5,5,0))
//                    .EndTime(LocalDateTime.of(2021, 7, 5, 12, 0)).build();
//
////            companyA.employerCreateCompany(employerA);
////            companyB.employerCreateCompany(employerA);
////            companyC.employerCreateCompany(employerB);
////            companyD.employerCreateCompany(employerB);
////
//            em.persist(companyA);
////            em.persist(companyB);
////            em.persist(companyC);
////            em.persist(companyD);
////
////            em.persist(joinCompanyB);
////            em.persist(joinCompanyC);
////
////            em.persist(joinCompanyOne);
////            em.persist(joinCompanyTwo);
////            em.persist(joinCompanyThree);
////            em.persist(joinCompanyFour);
////            em.persist(joinCompanyFive);
////            em.persist(joinCompanySix);
////            em.persist(joinCompanySeven);
////            em.persist(joinCompanyEight);
////            em.persist(joinCompanyNine);
////            em.persist(joinCompanyTen);
////            em.persist(joinCompanyTenA);
////            em.persist(joinCompanyTenB);
////            em.persist(joinCompanyTenC);
////
//            em.persist(commute);
//            em.persist(commuteB);
//            em.persist(commuteC);
////
////            Notice noticeA = Notice.builder().title("안녕하세요 이선제 입니다.").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeB = Notice.builder().title("개인정보처리방침 일부 변경에 관한 안내").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeC = Notice.builder().title("개인정보처리방침 일부 변경에 관한 안내").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeD = Notice.builder().title("개인정보처리방침 일부 변경에 관한 안내").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeE = Notice.builder().title("개인정보처리방침 일부 변경에 관한 안내").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeF = Notice.builder().title("개인정보").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////            Notice noticeG = Notice.builder().title("공지사항").contents("contents")
////                    .member(workerA).company(companyA).date("2021-06-21").build();
////
////            em.persist(noticeA);
////            em.persist(noticeB);
////            em.persist(noticeC);
////            em.persist(noticeD);
////            em.persist(noticeE);
////            em.persist(noticeF);
////            em.persist(noticeG);
////
////
////
////
////        }
////    }
//        }
//    }
//}
