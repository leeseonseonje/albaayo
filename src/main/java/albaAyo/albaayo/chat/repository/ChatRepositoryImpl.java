package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.domain.Chat;
import albaAyo.albaayo.chat.domain.QChat;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static albaAyo.albaayo.chat.domain.QChat.*;
import static albaAyo.albaayo.member.domain.QMember.*;

public class ChatRepositoryImpl implements ChatRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ChatRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Chat> chatContents(Long companyId) {
        return  queryFactory.select(chat)
                .from(chat)
                .where(chat.company.id.eq(companyId))
                .join(member).fetchJoin()
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
