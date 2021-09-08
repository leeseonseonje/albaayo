package albaAyo.albaayo.domains.schedule.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public List<ResponseScheduleListDto> scheduleList(Long companyId, String date) {
//
//        return queryFactory.select(Projections.constructor(ResponseScheduleListDto.class,
//                schedule.id, schedule.workSchedule))
//                .from(schedule)
//                .where(schedule.company.id.eq(companyId)
//                        .and(schedule.date.eq(date)))
//                .orderBy(schedule.id.desc())
//                .fetch();
//    }
}
