package albaAyo.albaayo.domains.chat.repository;

import albaAyo.albaayo.domains.chat.dto.ResponseChatMessage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.domains.chat.domain.QChat.*;
import static albaAyo.albaayo.domains.member.domain.QMember.*;

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
                member.id, member.name, chat.chatContents, chat.createdDate))
                .from(chat)
                .where(chat.company.id.eq(companyId))
                .join(member)
                .on(chat.member.id.eq(member.id))
                .orderBy(chat.createdDate.asc())
                .fetch();
    }

    public void test(Long companyId) {
         queryFactory.select(chat)
                .from(chat)
                .where(chat.company.id.eq(companyId))
                .join(member).fetchJoin()
                .orderBy(chat.createdDate.asc())
                .fetch();
    }
}
