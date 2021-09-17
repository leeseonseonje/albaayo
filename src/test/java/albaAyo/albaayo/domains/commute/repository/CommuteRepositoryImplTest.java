package albaAyo.albaayo.domains.commute.repository;

import albaAyo.albaayo.domains.commute.Commute;
import albaAyo.albaayo.domains.commute.QCommute;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    public void localDateTimeTest() {
        LocalDateTime date = LocalDateTime.parse("1998-02-09 12:22",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("date = " + date);
    }
}