package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.company.QCompany.*;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Company> findCompanies(Long id) {

        Member findEmployer = em.find(Member.class, id);

        return queryFactory
                .selectFrom(company)
                .where(company.member.eq(findEmployer))
                .fetch();
    }
}
