package albaAyo.albaayo.notice.Service;


import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.QCompany;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.QMember;
import albaAyo.albaayo.notice.Notice;
import albaAyo.albaayo.notice.QNotice;
import albaAyo.albaayo.notice.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static albaAyo.albaayo.notice.QNotice.notice;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final EntityManager em;
    JPAQueryFactory queryFactory;

    //공지 등록
    public Notice regisger(Long id, Company company, Member member, String title, String contents, String image){
        Notice notice = new Notice(id,company,member,title,contents,image);
        validateDuplicateNotice(notice);
        noticeRepository.save(notice);
        return notice;
    }
    // 그룹확인


    private void validateDuplicateNotice(Notice notice) {
        Optional<Notice> findId = noticeRepository.findById(notice.getId());
        if(!findId.isEmpty()) {
            throw  new IllegalStateException("중복 제목 공지 입니다.");
        }
    }

    //공지수정
    public Notice revisenotice(Long id,String newtitle,String newcontext,String newimage) {
        queryFactory  = new JPAQueryFactory(em);
        Notice result = queryFactory
                .select(notice)
                .from(notice)
                .fetchOne();

        System.out.println("result = " + result);
        if(newcontext != result.getContents()){
            result.changeContents(newcontext);
        }
        if(newtitle != result.getTitle()){
            result.changetitle(newtitle);
        }
        if(newimage != result.getImage()){
            result.changemage(newimage);
        }

        return result;
    }

    //게시자명 검색
    public List<Notice> findNoticeByName(Long company_id,String name) {
        queryFactory  = new JPAQueryFactory(em);
        List<Notice> result = queryFactory
                .select(notice)
                .from(notice).join(notice.member,QMember.member).join(notice.company,QCompany.company)
                .where(notice.member.name.eq(name) , notice.company.id.eq(company_id))
                .fetch();

        return result;
    }

    //공지 제목 검색
    public List<Notice> findNoticeBytitle(Long company_id,String title) {
        queryFactory = new JPAQueryFactory(em);
        List<Notice> result = queryFactory
                .select(notice)
                .from(notice).join(notice.company, QCompany.company)
                .where(notice.company.id.eq(company_id), notice.title.eq(title))
                .fetch();
        return result;
    }

    //공지 삭제
    public void removeNotice(Notice notice) {
        noticeRepository.delete(notice);
    }


}
