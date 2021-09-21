package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.chat.domain.QPersonalChat;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.jdo.annotations.Join;
import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.domains.chat.domain.QPersonalChat.*;
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
    public List<Company> findCompanies(Long id) {
        return queryFactory
                .select(company)
                .from(company)
                .where(company.member.id.eq(id))
                .orderBy(company.name.asc())
                .fetch();
    }

    //직원이 없을경우 join_company 테이블이 비어있으므로 empty list가 조회된다.
    @Override
    public List<ResponseCompanyWorkerListDto> findCompanyWorkerList(Long companyId) {
        return queryFactory
                .select(Projections.constructor(ResponseCompanyWorkerListDto.class,
                        member.id, member.name, member.birth, member.role, personalChat.count()))
                .distinct()
                .from(company)
                .join(joinCompany)
                .on(joinCompany.company.id.eq(companyId)
                        .and(joinCompany.accept.eq(Accept.ACCEPT)))
                .join(member)
                .on(member.id.eq(company.member.id).or(member.id.eq(joinCompany.member.id)))
                .join(personalChat.recvMember, member)
                .orderBy(member.role.asc())
                .fetch();
    }

    //직원이 없을경우
    @Override
    public ResponseCompanyWorkerListDto findCompanyEmployer(Long companyId) {
        return queryFactory
                .select(Projections.constructor(ResponseCompanyWorkerListDto.class,
                        member.id, member.name, member.birth, member.role))
                .from(company)
                .join(company.member, member)
                .where(company.id.eq(companyId))
                .fetchOne();
    }

    @Override
    public Long test() {
        return queryFactory
                .select(member.count())
                .from(member)
                .where(member.id.eq(1L))
                .fetchOne();
    }

}
