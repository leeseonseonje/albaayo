package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.domains.company.domain.QCompany.*;
import static albaAyo.albaayo.domains.company.domain.QJoinCompany.*;
import static albaAyo.albaayo.domains.member.domain.QMember.*;


public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<CompanyDto> findCompanies(Long id) {
        return queryFactory
                .select(Projections.constructor(CompanyDto.class,
                        company.id, company.name, company.location, company.picture))
                .from(company)
                .where(company.member.id.eq(id))
                .orderBy(company.name.asc())
                .fetch();
    }

    @Override
    public List<ResponseCompanyWorkerListDto> findCompanyWorkerList(Long companyId) {
        return queryFactory
                .select(Projections.constructor(ResponseCompanyWorkerListDto.class,
                        member.id, member.name, member.birth, member.role))
                .distinct()
                .from(company)
                .where(company.id.eq(companyId))
                .join(joinCompany)
                .on(joinCompany.company.id.eq(companyId)
                        .and(joinCompany.accept.eq(Accept.ACCEPT)))
                .join(member)
                .on(member.id.eq(company.member.id).or(member.id.eq(joinCompany.member.id)))
                .orderBy(member.role.asc())
                .fetch();
    }

    @Override
    public ResponseCompanyWorkerListDto findCompanyEmployer(Long companyId) {
        return queryFactory
                .select(Projections.constructor(ResponseCompanyWorkerListDto.class,
                        member.id, member.name, member.birth, member.role))
                .from(company)
                .where(company.id.eq(companyId))
                .join(member)
                .on(member.id.eq(company.member.id))
                .fetchOne();
    }
}
