package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.domains.company.domain.QCompany.*;
import static albaAyo.albaayo.domains.company.domain.QJoinCompany.*;
import static albaAyo.albaayo.domains.member.domain.QMember.*;


public class JoinCompanyRepositoryImpl implements JoinCompanyRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public JoinCompanyRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<JoinCompany> acceptCompanyList(Long workerId, Accept accept) {

        return queryFactory
                .select(joinCompany)
                .from(joinCompany)
                .join(joinCompany.company, company).fetchJoin()
                .join(joinCompany.member, member).fetchJoin()
                .where(joinCompany.member.id.eq(workerId)
                        .and(joinCompany.accept.eq(accept)))
                .orderBy(company.name.asc())
                .fetch();
    }

//    @Override
//    public List<Company> acceptCompanyList(Long workerId, Accept accept) {
//
//        return queryFactory
//                .select(company) -> select는 company
//                .from(joinCompany) -> from은 joinCompany
//                .join(joinCompany.company, company).fetch join()
//                .where(joinCompany.member.id.eq(workerId)
//	              .and(joinCompany.accept.eq(accept)))
//                .orderBy(company.name.asc())
//                .fetch();

//                  =>객체그래프가 맞지않음
//	                ->fetch join을 사용하면 에러가남
//    }

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

    @Override
    public List<String> companyWorkers(Long companyId, Long memberId) {
        return queryFactory
                .select(member.fcmToken)
                .from(joinCompany)
                .where(joinCompany.company.id.eq(companyId)
                        .and(joinCompany.accept.eq(Accept.ACCEPT))
                        .and(joinCompany.member.id.eq(memberId)))
                .join(member)
                .on(joinCompany.member.id.eq(member.id))
                .fetch();
    }
}
