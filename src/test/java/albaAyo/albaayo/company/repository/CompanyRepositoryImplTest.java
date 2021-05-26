package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.*;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.dto.QCompanyAcceptDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.jdo.annotations.Join;
import javax.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

import static albaAyo.albaayo.company.domain.QJoinCompany.joinCompany;

@SpringBootTest
@Transactional
class CompanyRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    CompanyRepository companyRepository;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void mainTest() {
        Company findCompany = companyRepository.findByCompanyMain(6L)
                .orElseThrow(() -> new RuntimeException("ERROR"));

        String companyName = findCompany.getName();// 이름
        System.out.println("companyName = " + companyName);
        String employerName = findCompany.getMember().getName();// 사장
        System.out.println("employerName = " + employerName);
        Collection<JoinCompany> joinCompanies = findCompany.getJoinCompanies();
        for (JoinCompany joinCompany : joinCompanies) {
            System.out.println(joinCompany.getMember().getName());
        }
    }

    @Test
    public void t() {
        Long l = 1L;
        List<CompanyAcceptDto> result = queryFactory
                .select(new QCompanyAcceptDto(QCompany.company.id, QCompany.company.name, QCompany.company.location))
                .from(QJoinCompany.joinCompany)
                .join(QCompany.company)
                .on(QCompany.company.eq(QJoinCompany.joinCompany.company))
                .where(QJoinCompany.joinCompany.member.id.eq(5L)
                        .and(QJoinCompany.joinCompany.accept.eq(Accept.ACCEPT)))
                .fetch();

        for (CompanyAcceptDto company : result) {
            System.out.println("company = " + company.getName());
            System.out.println("company = " + company.getLocation());
        }
    }

    @Test
    public void companyAccept() {

        long execute = queryFactory
                .update(QJoinCompany.joinCompany)
                .set(QJoinCompany.joinCompany.accept, Accept.ACCEPT)
                .where(QJoinCompany.joinCompany.member.id.eq(4L).and(QJoinCompany.joinCompany.company.id.eq(6L)))
                .execute();

        em.flush();
        em.clear();

        List<JoinCompany> join = queryFactory
                .selectFrom(QJoinCompany.joinCompany)
                .fetch();

        for (JoinCompany company : join) {
            System.out.println(company.getAccept());
        }

//        Assertions.assertThat(execute).isEqualTo(1L);
    }
}
