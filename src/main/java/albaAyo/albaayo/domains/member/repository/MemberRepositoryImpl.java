package albaAyo.albaayo.domains.member.repository;

import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
//
//    @Override
//    public Member findByFcmToken(String fcmToken) {
//        return queryFactory
//                .selectFrom(QMember.member)
//                .where(QMember.member.fcmToken.eq(fcmToken))
//                .fetchOne();
//    }
}
