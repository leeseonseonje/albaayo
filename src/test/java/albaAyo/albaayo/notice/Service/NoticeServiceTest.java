package albaAyo.albaayo.notice.Service;


import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import albaAyo.albaayo.notice.Notice;
import albaAyo.albaayo.notice.repository.NoticeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class) //spring 과 함께실행
@SpringBootTest //spring boot 를 띄운상태로 실행 autowired 관련
@Transactional
public class NoticeServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    NoticeService noticeService;

    @Test
    @Commit
    public void 공지작성자검색() throws  Exception {
    //given
        Member member1 = new Member("userId1","password1","ddd@dddd","ㅇㅇ","1992", Role.ROLE_EMPLOYER);
        Company company1 = new Company("19990","company1","location");
        Member member2 = new Member("userId","pasord1","ddd@dd","zz","1992",Role.ROLE_EMPLOYER);

        em.persist(member1);
        em.persist(company1);
        em.persist(member2);
        noticeService.regisger(1L,company1,member1,"공지사항1","내용1","경로");
        noticeService.regisger(2L,company1,member2,"공지사항2","내용","경로");

        //when
        List<Notice> result= noticeService.findNoticeByName(company1.getId(),"ㅇㅇ");

        //then
        Assertions.assertEquals(result.get(0).getMember().getName(),"ㅇㅇ");
        Assertions.assertEquals(result.get(0).getContents(),"내용1");
    }

    @Test
    public void 공지제목검색() throws Exception {
        Member member1 = new Member("userId1","password1","ddd@dddd","ㅇㅇ","1992",Role.ROLE_EMPLOYER);
        Company company1 = new Company("19990","company1","location");
        Member member2 = new Member("userId","pasord1","ddd@dd","zz","1992",Role.ROLE_EMPLOYER);


        em.persist(member1);
        em.persist(company1);
        em.persist(member2);
        em.persist(company1);
        noticeService.regisger(1L,company1,member1,"공지사항1","내용1","경로");
        noticeService.regisger(2L,company1,member2,"공지사항2","내용","경로");

        //when
        List<Notice> findNotice = noticeService.findNoticeBytitle(company1.getId(),"공지사항1");

        //then
        assertEquals(findNotice.get(0).getTitle(),"공지사항1");
    }

    @Test
    @Commit
    public void 공지수정() throws Exception{
        //given
        Member member1 = new Member("userId1","password1","ddd@dddd","ㅇㅇ","1992",Role.ROLE_EMPLOYER);
        Company company1 = new Company("19990","company1","location");

        em.persist(member1);
        em.persist(company1);
        Notice notice = noticeService.regisger(1L,company1,member1,"공지사항1","내용1","경로");
        //when
        notice = noticeService.revisenotice(notice.getId(), "공지사항2","내용2","");

        //then
        assertEquals(notice.getTitle(),"공지사항2");
        assertEquals(notice.getContents(),"내용2");

    }
    //공지 띄우기


}
//    @Test
//    @Commit
//    public void 공지등록() throws  Exception {
//        //given
//        Member member1 = new Member("userId1","password1","ddd@dddd","ㅇㅇ","1992",Role.ROLE_EMPLOYER);
//        Company company1 = new Company("19990","company1","location");
//        em.persist(member1);
//        em.persist(company1);
//        Notice notice = new Notice(1L,company1,member1,"공지사항1","내용","경로");
////        Notice notice2 = new Notice(1L,company1,member1,"공지사항1","내용","경로");
//
//        //when
//        Long saveId = noticeService.regisger(notice);
//
//        //then
//        Assertions.assertEquals(1L,saveId);
//    }