package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.QChat;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.member.domain.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ChatRepositoryImpl implements ChatRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ChatRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseChatMessage> chatContents(Long companyId) {
        return queryFactory.select(Projections.constructor(ResponseChatMessage.class,
                QMember.member.id, QMember.member.name, QChat.chat.chatContents, QChat.chat.createdDate))
                .from(QChat.chat)
                .where(QChat.chat.company.id.eq(companyId))
                .join(QMember.member)
                .on(QChat.chat.member.id.eq(QMember.member.id))
                .orderBy(QChat.chat.createdDate.asc())
                .fetch();
    }
}
