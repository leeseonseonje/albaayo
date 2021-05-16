
package albaAyo.albaayo;

import albaAyo.albaayo.company.Company;
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
                    .name("workerA")
                    .birth("1998.02.09")
                    .role(Role.ROLE_WORKER)
                    .build();

            Member workerB = Member.builder()
                    .userId("workerB")
                    .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi")
                    .email("seon9323@gddddddd.com")
                    .name("workerB")
                    .birth("1998.02.09")
                    .role(Role.ROLE_WORKER)
                    .build();

            em.persist(employerA);
            em.persist(employerB);
            em.persist(workerA);
            em.persist(workerB);

            Company companyA = Company.builder()
                    .name("A")
                    .location("대구")
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

            companyA.employerCreateCompany(employerA);
            companyB.employerCreateCompany(employerA);
            companyC.employerCreateCompany(employerB);
            companyD.employerCreateCompany(employerB);

            em.persist(companyA);
            em.persist(companyB);
            em.persist(companyC);
            em.persist(companyD);
        }
    }
}
