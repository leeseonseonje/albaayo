package albaAyo.albaayo.notice.Service;


import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.repository.MemberRepository;
import albaAyo.albaayo.notice.domain.Notice;
import albaAyo.albaayo.notice.domain.NoticeImage;
import albaAyo.albaayo.notice.dto.*;
import albaAyo.albaayo.notice.repository.NoticeImageRepository;
import albaAyo.albaayo.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;

    //공지 등록
    public void noticeRegister(Long memberId, Long companyId, RequestNoticeDto requestNoticeDto) throws IOException {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 멤버 입니다."));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("존재하지 않는 회사 입니다."));

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Notice notice = Notice.builder().member(member).company(company)
                .title(requestNoticeDto.getTitle()).contents(requestNoticeDto.getContents()).date(date).build();
        Notice savedNotice = noticeRepository.save(notice);

        if (requestNoticeDto.getImage().size() != 0) {
            List<NoticeImage> noticeImages = imageUpload(requestNoticeDto.getImage(), savedNotice);
            noticeImageRepository.saveAll(noticeImages);
        }
    }

    public List<NoticeImage> imageUpload(List<NoticeImageDto> list, Notice notice) throws IOException {

        List<NoticeImage> result = new ArrayList<>();
        for (NoticeImageDto noticeImageDto : list) {
            byte[] bytes = Base64.decodeBase64(noticeImageDto.getImage());
            UUID uuid = UUID.randomUUID();
            FileImageOutputStream image = new FileImageOutputStream(
                    new File("/home/ec2-user/groupNotice" + uuid.toString() + ".jpeg"));
            image.write(bytes, 0, bytes.length);
            image.close();
            result.add(NoticeImage.builder().notice(notice)
                    .image("/home/ec2-user/groupNotice" + uuid.toString() + ".jpeg")
                    .imageContent(noticeImageDto.getImageContent()).build());
        }
        return result;
    }

//    //공지수정
//    public void noticeUpdate(RequestNoticeUpdateDto requestNoticeUpdateDto) throws IOException {
//        Notice notice = noticeRepository.findById(requestNoticeUpdateDto.getNoticeId())
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 공지사항 입니다."));
//        String imagePath = null;
////        if (requestNoticeUpdateDto.getImage() != null) {
////            UUID uuid = imageUpload(requestNoticeUpdateDto.getImage());
////            imagePath = "C:\\Users\\seon\\groupNotice\\" + uuid.toString() + ".jpg";
////        }
////        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
////        requestNoticeUpdateDto.setImage(imagePath);
////        notice.updateNotice(requestNoticeUpdateDto, date);
//    }

    //조회
    public Page<ResponseNoticeListDto> noticeList(Long companyId, int page) {
        return noticeRepository.noticeList(companyId, PageRequest.of(page, 20));
    }

    public ResponseNoticeDto noticeContent(Long noticeId) throws IOException {
        ResponseNoticeDto notice = noticeRepository.noticeContent(noticeId);
        List<NoticeImageDto> imageList = noticeImageRepository.findNoticeImage(noticeId);
        if (imageList != null) {
            imageDownload(imageList);
            notice.setImageList(imageList);
        }
        return notice;
    }

    private void imageDownload(List<NoticeImageDto> imageList) throws IOException {
        for (NoticeImageDto noticeImageDto : imageList) {
            Path path = Paths.get(noticeImageDto.getImage());
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            String image = Base64.encodeBase64String(bytes);
            noticeImageDto.setImage(image);
        }
    }

    //게시자명 검색
//    public List<Notice> findNoticeByName(Long company_id,String name) {
//        queryFactory  = new JPAQueryFactory(em);
//        List<Notice> result = queryFactory
//                .select(notice)
//                .from(notice).join(notice.member,QMember.member).join(notice.company,QCompany.company)
//                .where(notice.member.name.eq(name) , notice.company.id.eq(company_id))
//                .fetch();
//
//        return result;
//    }

    //공지 제목 검색
//    public List<Notice> findNoticeBytitle(Long company_id,String title) {
//        queryFactory = new JPAQueryFactory(em);
//        List<Notice> result = queryFactory
//                .select(notice)
//                .from(notice).join(notice.company, QCompany.company)
//                .where(notice.company.id.eq(company_id), notice.title.eq(title))
//                .fetch();
//        return result;
//    }

    //공지 삭제
    public void removeNotice(Notice notice) {
        noticeRepository.delete(notice);
    }
}
