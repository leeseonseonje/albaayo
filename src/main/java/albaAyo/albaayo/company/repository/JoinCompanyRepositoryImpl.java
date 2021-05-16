package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.dto.QCompanyAcceptDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.company.domain.QCompany.*;
import static albaAyo.albaayo.company.domain.QJoinCompany.*;
import static albaAyo.albaayo.company.domain.Accept.*;

public class JoinCompanyRepositoryImpl implements JoinCompanyRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public JoinCompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CompanyAcceptDto> CompanyAcceptList(Long workerId, Accept accept) {
        return queryFactory
                .select(new QCompanyAcceptDto(company.id, company.name, company.location))
                .from(joinCompany)
                .join(company)
                .on(company.eq(joinCompany.company))
                .where(joinCompany.member.id.eq(workerId)
                        .and(joinCompany.workerInvite.eq(accept)))
                .fetch();
    }
}
