package albaAyo.albaayo.notice.service;


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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    public static final String URL = "/home/ec2-user/groupNotice/";

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

        imageSave(requestNoticeDto, savedNotice);
    }

    private void imageSave(RequestNoticeDto requestNoticeDto, Notice savedNotice) throws IOException {
        if (!requestNoticeDto.getImage().isEmpty()) {
           noticeImageRepository.saveAll(imageUpload(requestNoticeDto.getImage(), savedNotice));
        }
    }

    //공지수정
    public void noticeUpdate(RequestNoticeUpdateDto requestNoticeUpdateDto) throws IOException {
        Notice notice = noticeRepository.findById(requestNoticeUpdateDto.getNoticeId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 공지사항 입니다."));

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        noticeImageUpdate(requestNoticeUpdateDto, notice, date);
    }

    //수정(효율)
    private void noticeImageUpdate(RequestNoticeUpdateDto requestNoticeUpdateDto, Notice notice, String date) throws IOException {
        notice.updateNotice(requestNoticeUpdateDto, date);
        noticeImageRepository.noticeImageDelete(notice.getId());

        if (!requestNoticeUpdateDto.getImageList().isEmpty()) {
            noticeImageRepository.saveAll(imageUpload(requestNoticeUpdateDto.getImageList(), notice));
        }
    }

    //조회
    public Page<ResponseNoticeListDto> noticeList(Long companyId, int page) {
        return noticeRepository.noticeList(companyId, PageRequest.of(page, 20));
    }

    public ResponseNoticeDto noticeContent(Long noticeId) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));

        List<NoticeImageDto> noticeImageDtos = notice.getNoticeImages().stream()
                .map(m -> new NoticeImageDto(m.getImage(), m.getImageContent()))
                .collect(Collectors.toList());
        List<NoticeImageDto> list = imageDownload(noticeImageDtos);

        return notice.noticeDtoBuilder(notice, list);
    }

    public List<NoticeImage> imageUpload(List<NoticeImageDto> list, Notice notice) throws IOException {

        List<NoticeImage> result = new ArrayList<>();

        for (NoticeImageDto noticeImageDto : list) {
            byte[] bytes = Base64.decodeBase64(noticeImageDto.getImage());
            UUID uuid = UUID.randomUUID();
            FileImageOutputStream image = new FileImageOutputStream(
                    new File(URL + uuid.toString() + ".jpeg"));
            image.write(bytes, 0, bytes.length);
            image.close();
            result.add(NoticeImage.builder().notice(notice)
                    .image(URL + uuid.toString() + ".jpeg")
                    .imageContent(noticeImageDto.getImageContent()).build());
        }

        return result;
    }

    private List<NoticeImageDto> imageDownload(List<NoticeImageDto> imageList) throws IOException {
        for (NoticeImageDto noticeImageDto : imageList) {
            Path path = Paths.get(noticeImageDto.getImage());
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            String image = Base64.encodeBase64String(resource.getInputStream().readAllBytes());
            noticeImageDto.setImage(image);
        }
        return imageList;
    }

    //공지 삭제
    public void removeNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
        noticeRepository.delete(notice);
    }
}
