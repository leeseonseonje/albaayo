package albaAyo.albaayo.domains.notice.service;


import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import albaAyo.albaayo.domains.notice.domain.Notice;
import albaAyo.albaayo.domains.notice.dto.*;
import albaAyo.albaayo.domains.notice.repository.NoticeImageRepository;
import albaAyo.albaayo.domains.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    @Value("${path.notice}")
    private String path;

    private final FcmService fcmService;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    //공지 등록
    public void noticeRegister(Long memberId, Long companyId, RequestNoticeDto requestNoticeDto) throws IOException, ExecutionException, InterruptedException {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 멤버 입니다."));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("존재하지 않는 회사 입니다."));

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Notice notice = Notice.builder().member(member).company(company)
                .title(requestNoticeDto.getTitle()).contents(requestNoticeDto.getContents()).date(date).build();
        Notice savedNotice = noticeRepository.save(notice);

        imageSave(requestNoticeDto, savedNotice);
        fcmNotification(company, member);
    }

    private void imageSave(RequestNoticeDto requestNoticeDto, Notice savedNotice) throws IOException {
        if (!requestNoticeDto.getImage().isEmpty()) {
           noticeImageRepository.saveAll(savedNotice.imageUpload(requestNoticeDto.getImage(), savedNotice, path));
        }
    }

    private void fcmNotification(Company company, Member member) throws ExecutionException, InterruptedException {

        List<String> fcmTokens = joinCompanyRepository.companyWorkers(company.getId(), member.getId());
        if (!company.getMember().getId().equals(member.getId())) {
            fcmTokens.add(company.getMember().getFcmToken());
        }

        String fcmBody = member.getName() + "님이 공지사항을 게시하였습니다.";
        fcmService.sendMulticastMessage(fcmTokens, company.getName(), fcmBody);
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
            noticeImageRepository.saveAll(notice.imageUpload(requestNoticeUpdateDto.getImageList(), notice, path));
        }
    }

    //조회
    public Page<ResponseNoticeListDto> noticeList(Long companyId, int page) {
        return noticeRepository.noticeList(companyId, PageRequest.of(page, 20));
    }

    public ResponseNoticeDto noticeContent(Long noticeId) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));

        List<NoticeImageDto> list = getNoticeImageDtos(notice);

        return notice.noticeDtoBuilder(notice, list);
    }

    private List<NoticeImageDto> getNoticeImageDtos(Notice notice) throws IOException {
        List<NoticeImageDto> noticeImageDtos = notice.getNoticeImages().stream()
                .map(m -> new NoticeImageDto(m.getImage(), m.getImageContent()))
                .collect(Collectors.toList());
        List<NoticeImageDto> list = notice.imageDownload(noticeImageDtos);
        return list;
    }

    //공지 삭제
    public void removeNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
        noticeRepository.delete(notice);
    }
}
