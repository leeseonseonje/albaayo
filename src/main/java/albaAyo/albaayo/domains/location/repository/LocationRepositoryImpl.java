package albaAyo.albaayo.domains.location.repository;


import albaAyo.albaayo.domains.location.Location;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static albaAyo.albaayo.domains.location.QLocation.*;

public class LocationRepositoryImpl implements LocationRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public LocationRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Location location(Long workerId, Long companyId) {
        return queryFactory
                .select(location)
                .from(location)
                .where(location.member.id.eq(workerId)
                        .and(location.company.id.eq(companyId)))
                .fetchOne();
    }
}
