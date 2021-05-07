package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.QMember;
import albaAyo.albaayo.member.employer.Employer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.company.QCompany.*;
import static albaAyo.albaayo.member.domain.QMember.member;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Company> findCompanies(Long id) {

        Employer findEmployer = (Employer) em.find(Member.class, id);

        return queryFactory
                .selectFrom(company)
                .where(company.employer.eq(findEmployer))
                .fetch();
    }
}
