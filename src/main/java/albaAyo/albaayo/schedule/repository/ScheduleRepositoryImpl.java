package albaAyo.albaayo.schedule.repository;

import albaAyo.albaayo.schedule.domain.QSchedule;
import albaAyo.albaayo.schedule.dto.ResponseScheduleListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.schedule.domain.QSchedule.*;

public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseScheduleListDto> scheduleList(Long companyId, Integer date) {

        return queryFactory.select(Projections.constructor(ResponseScheduleListDto.class,
                schedule.id, schedule.workSchedule))
                .from(schedule)
                .where(schedule.company.id.eq(companyId)
                        .and(schedule.date.eq(date)))
                .orderBy(schedule.id.desc())
                .fetch();
    }
}
