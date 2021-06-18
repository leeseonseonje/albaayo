package albaAyo.albaayo.notice.Service;


import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.QCompany;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.QMember;
import albaAyo.albaayo.member.repository.MemberRepository;
import albaAyo.albaayo.notice.Notice;
import albaAyo.albaayo.notice.dto.RequestNoticeDto;
import albaAyo.albaayo.notice.dto.RequestNoticeUpdateDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeListDto;
import albaAyo.albaayo.notice.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.stream.FileImageOutputStream;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static albaAyo.albaayo.notice.QNotice.notice;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final NoticeRepository noticeRepository;
    private final EntityManager em;
    JPAQueryFactory queryFactory;

    //공지 등록
    public void noticeRegister(Long memberId, Long companyId, RequestNoticeDto requestNoticeDto) throws IOException {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 멤버 입니다."));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("존재하지 않는 회사 입니다."));
        String imagePath = null;
        if (requestNoticeDto.getImage() != null) {
            UUID uuid = imageUpload(requestNoticeDto.getImage());
            imagePath = "C:\\Users\\seon\\groupNotice\\" + uuid.toString() + ".jpg";
        }
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        Notice notice = Notice.builder().member(member).company(company)
                .title(requestNoticeDto.getTitle()).contents(requestNoticeDto.getContents())
                .image(imagePath).date(date).build();
        noticeRepository.save(notice);
    }

    public UUID imageUpload(String imageBase) throws IOException {
            byte[] bytes = Base64.decodeBase64(imageBase);
            UUID uuid = UUID.randomUUID();
            FileImageOutputStream image = new FileImageOutputStream(
                    new File("C:\\Users\\seon\\groupNotice\\" + uuid.toString() + ".jpg"));
            image.write(bytes, 0, bytes.length);
            image.close();

            return uuid;
    }

    //공지수정
    public void noticeUpdate(RequestNoticeUpdateDto requestNoticeUpdateDto) throws IOException {
        Notice notice = noticeRepository.findById(requestNoticeUpdateDto.getNoticeId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 공지사항 입니다."));
        String imagePath = null;
        if (requestNoticeUpdateDto.getImage() != null) {
            UUID uuid = imageUpload(requestNoticeUpdateDto.getImage());
            imagePath = "C:\\Users\\seon\\groupNotice\\" + uuid.toString() + ".jpg";
        }
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        requestNoticeUpdateDto.setImage(imagePath);
        notice.updateNotice(requestNoticeUpdateDto, date);
    }

    //조회
    public Page<ResponseNoticeListDto> noticeList(Long companyId, int page) {
        return noticeRepository.noticeList(companyId, PageRequest.of(page, 5));
    }

    public ResponseNoticeDto noticeContent(Long noticeId) throws IOException {
        ResponseNoticeDto notice = noticeRepository.noticeContent(noticeId);

        imageDownload(notice);
        return notice;
    }

    private void imageDownload(ResponseNoticeDto notice) throws IOException {
        if (notice.getImage() != null) {
            Path path = Paths.get(notice.getImage());
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String image = Base64.encodeBase64String(bytes);
            notice.setImage(image);
        }
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
