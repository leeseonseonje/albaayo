package albaAyo.albaayo.domains.notice.repository;

import albaAyo.albaayo.domains.member.domain.QMember;
import albaAyo.albaayo.domains.notice.domain.QNotice;
import albaAyo.albaayo.domains.notice.dto.ResponseNoticeListDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static albaAyo.albaayo.domains.company.domain.QCompany.*;


public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResponseNoticeListDto> noticeList(Long companyId, Pageable pageable) {

        QueryResults<ResponseNoticeListDto> results = queryFactory
                .select(Projections.constructor(ResponseNoticeListDto.class,
                        QNotice.notice.id, QMember.member.name, QNotice.notice.title, QNotice.notice.date))
                .from(QNotice.notice)
                .where(QNotice.notice.company.id.eq(companyId))
                .join(company)
                .on(QNotice.notice.company.id.eq(company.id))
                .join(QMember.member)
                .on(QNotice.notice.member.id.eq(QMember.member.id))
                .orderBy(QNotice.notice.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ResponseNoticeListDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
