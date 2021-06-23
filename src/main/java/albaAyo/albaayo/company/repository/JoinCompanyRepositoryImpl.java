package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.domain.QCompany;
import albaAyo.albaayo.company.domain.QJoinCompany;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.dto.CompanyDto;
import albaAyo.albaayo.company.dto.QCompanyAcceptDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.company.domain.QCompany.*;
import static albaAyo.albaayo.company.domain.QJoinCompany.*;


public class JoinCompanyRepositoryImpl implements JoinCompanyRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public JoinCompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CompanyDto> acceptCompanyList(Long workerId, Accept accept) {

        return queryFactory
                .select(Projections.constructor(CompanyDto.class,
                        company.id, company.name, company.location, company.picture))
                .from(joinCompany)
                .join(company)
                .on(company.eq(joinCompany.company))
                .where(joinCompany.member.id.eq(workerId)
                        .and(joinCompany.accept.eq(accept)))
                .orderBy(company.name.asc())
                .fetch();
    }

    @Override
    public Long notAcceptCompanyCount(Long workerId) {
        return queryFactory
                .selectFrom(joinCompany)
                .where(joinCompany.member.id.eq(workerId)
                        .and(joinCompany.accept.eq(Accept.NOT_ACCEPT)))
                .fetchCount();
    }

    @Override
    public void companyAccept(Long workerId, Long companyId) {
         queryFactory
                .update(joinCompany)
                .set(joinCompany.accept, Accept.ACCEPT)
                .where(joinCompany.member.id.eq(workerId).and(joinCompany.company.id.eq(companyId)))
                .execute();
    }
}
