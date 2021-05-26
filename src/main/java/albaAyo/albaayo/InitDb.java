package albaAyo.albaayo;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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

        public void dbInit1() {
            Member employerA = Member.builder().build().builder()
                    .userId("EMPLOYERA")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("leeseonje@gmail.com")
                    .name("employerA")
                    .birth("1998.02.09")
                    .role(Role.ROLE_EMPLOYER)
                    .build();

            Member employerB = Member.builder()
                    .userId("EMPLOYERB")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("seon9323@naver.com")
                    .name("employerB")
                    .birth("1998.02.09")
                    .role(Role.ROLE_EMPLOYER)
                    .build();

            Member workerA = Member.builder()
                    .userId("workerA")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("seon9323@g.com")
                    .name("이선제")
                    .birth("1998.02.09")
                    .role(Role.ROLE_WORKER)
                    .build();

            Member workerB = Member.builder()
                    .userId("workerB")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("seon9323@gddddddd.com")
                    .name("이선제")
                    .birth("1998.02.09")
                    .role(Role.ROLE_WORKER)
                    .build();

            Member workerC = Member.builder()
                    .userId("workerC")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("seon9323@dasgddddddd.com")
                    .name("이선제")
                    .birth("1998.02.09")
                    .role(Role.ROLE_WORKER)
                    .build();

            em.persist(employerA);
            em.persist(employerB);
            em.persist(workerA);
            em.persist(workerB);
            em.persist(workerC);

            Company companyA = Company.builder()
                    .name("영진전문대 복현캠퍼스")
                    .location("대구광역시 달성군 다사읍 서재로120 107동 905호")
                    .businessRegistrationNumber("1234567890")
                    .build();


            Company companyB = Company.builder()
                    .name("B")
                    .location("서울")
                    .businessRegistrationNumber("0987654321")
                    .build();

            Company companyC = Company.builder()
                    .name("C")
                    .location("서울")
                    .businessRegistrationNumber("32134254365")
                    .build();

            Company companyD = Company.builder()
                    .name("D")
                    .location("서울")
                    .businessRegistrationNumber("456548675")
                    .build();

            JoinCompany joinCompanyOne = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyTwo = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyThree = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyFour = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyFive = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanySix = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanySeven = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyEight = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyNine = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyTen = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyTenA = JoinCompany.builder()
                    .member(workerA)
                    .company(companyA)
                    .accept(Accept.NOT_ACCEPT)
                    .build();

            JoinCompany joinCompanyTenB = JoinCompany.builder()
                    .member(workerA)
                    .company(companyB)
                    .accept(Accept.NOT_ACCEPT)
                    .build();

            JoinCompany joinCompanyTenC = JoinCompany.builder()
                    .member(workerA)
                    .company(companyC)
                    .accept(Accept.NOT_ACCEPT)
                    .build();

            JoinCompany joinCompanyB = JoinCompany.builder()
                    .member(workerB)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            JoinCompany joinCompanyC = JoinCompany.builder()
                    .member(workerC)
                    .company(companyA)
                    .accept(Accept.ACCEPT)
                    .build();

            companyA.employerCreateCompany(employerA);
            companyB.employerCreateCompany(employerA);
            companyC.employerCreateCompany(employerB);
            companyD.employerCreateCompany(employerB);

            em.persist(companyA);
            em.persist(companyB);
            em.persist(companyC);
            em.persist(companyD);

            em.persist(joinCompanyB);
            em.persist(joinCompanyC);

            em.persist(joinCompanyOne);
            em.persist(joinCompanyTwo);
            em.persist(joinCompanyThree);
            em.persist(joinCompanyFour);
            em.persist(joinCompanyFive);
            em.persist(joinCompanySix);
            em.persist(joinCompanySeven);
            em.persist(joinCompanyEight);
            em.persist(joinCompanyNine);
            em.persist(joinCompanyTen);
            em.persist(joinCompanyTenA);
            em.persist(joinCompanyTenB);
            em.persist(joinCompanyTenC);
        }
    }
}
