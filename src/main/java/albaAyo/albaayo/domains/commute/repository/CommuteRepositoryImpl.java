package albaAyo.albaayo.domains.commute.repository;

import albaAyo.albaayo.domains.commute.Commute;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static albaAyo.albaayo.domains.commute.QCommute.*;

public class CommuteRepositoryImpl implements CommuteRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CommuteRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Commute commute(Long workerId, Long companyId) {

        return queryFactory.selectFrom(commute)
                .where(commute.member.id.eq(workerId)
                        .and(commute.company.id.eq(companyId)))
                .orderBy(commute.startTime.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<Commute> commuteList(Long workerId, Long companyId) {

        return queryFactory.select(commute)
                .from(commute)
                .where(commute.member.id.eq(workerId)
                        .and(commute.company.id.eq(companyId)))
                .orderBy(commute.startTime.desc())
                .fetch();
    }

    @Override
    public List<Commute> monthCommuteList(Long workerId, Long companyId, LocalDateTime date) {

        return queryFactory.select(commute)
                .from(commute)
                .where(commute.member.id.eq(workerId)
                        .and(commute.company.id.eq(companyId))
                        .and(commute.startTime.year().eq(date.getYear())
                                .and(commute.startTime.month().eq(date.getMonthValue()))))
                .fetch();
    }
}
