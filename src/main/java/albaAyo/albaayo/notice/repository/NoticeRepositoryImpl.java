package albaAyo.albaayo.notice.repository;

import albaAyo.albaayo.company.domain.QCompany;
import albaAyo.albaayo.member.domain.QMember;
import albaAyo.albaayo.notice.domain.Notice;
import albaAyo.albaayo.notice.domain.QNotice;
import albaAyo.albaayo.notice.dto.ResponseNoticeDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeListDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static albaAyo.albaayo.company.domain.QCompany.*;


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
                .join(company).fetchJoin()
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
