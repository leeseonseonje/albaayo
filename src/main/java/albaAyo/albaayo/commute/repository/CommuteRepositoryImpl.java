package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.QCommute;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static albaAyo.albaayo.commute.QCommute.*;

public class CommuteRepositoryImpl implements CommuteRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CommuteRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Commute offWork(Long workerId, Long companyId) {

        return queryFactory.selectFrom(commute)
                .where(commute.member.id.eq(workerId)
                        .and(commute.company.id.eq(companyId)))
                .orderBy(commute.startTime.desc())
                .limit(1)
                .fetchOne();
    }
}
