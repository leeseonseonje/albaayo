package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.QCommute;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static albaAyo.albaayo.commute.QCommute.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommuteRepositoryImplTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void 퇴근테스트() {
        Commute commute = queryFactory.selectFrom(QCommute.commute)
                .where(QCommute.commute.member.id.eq(3L)
                        .and(QCommute.commute.company.id.eq(6L)))
                .orderBy(QCommute.commute.startTime.desc())
                .limit(1)
                .fetchOne();

        System.out.println("commute = " + commute.getId());
    }

}