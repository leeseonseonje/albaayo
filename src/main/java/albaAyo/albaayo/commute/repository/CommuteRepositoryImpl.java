package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static albaAyo.albaayo.commute.QCommute.*;

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
    public List<ResponseCommuteListDto> commuteList(Long workerId, Long companyId) {

        return queryFactory.select(Projections.constructor(ResponseCommuteListDto.class,
                commute.id, commute.startTime, commute.endTime))
                .from(commute)
                .where(commute.member.id.eq(workerId)
                        .and(commute.company.id.eq(companyId)))
                .orderBy(commute.startTime.asc())
                .fetch();
    }
}
