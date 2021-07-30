package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.domain.QPersonalChat;
import albaAyo.albaayo.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.member.domain.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.chat.domain.QPersonalChat.*;
import static albaAyo.albaayo.member.domain.QMember.*;

public class PersonalChatRepositoryImpl implements PersonalChatRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PersonalChatRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId) {
        return queryFactory.select(Projections.constructor(ResponsePersonalChatMessage.class,
                personalChat.sendMember.id, personalChat.recvMember.id, member.name, personalChat.chatContent,
                personalChat.createdDate))
                .from(personalChat)
                .where(personalChat.sendMember.id.eq(myMemberId).and(personalChat.recvMember.id.eq(memberId))
                        .or(personalChat.sendMember.id.eq(memberId).and(personalChat.recvMember.id.eq(myMemberId))))
                .join(member)
                .on(member.id.eq(personalChat.sendMember.id))
                .orderBy(personalChat.createdDate.asc())
                .fetch();
    }
}
