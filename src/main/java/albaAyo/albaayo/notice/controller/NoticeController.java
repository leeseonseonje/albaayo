package albaAyo.albaayo.notice.controller;

import albaAyo.albaayo.notice.service.NoticeService;
import albaAyo.albaayo.notice.dto.RequestNoticeDto;
import albaAyo.albaayo.notice.dto.RequestNoticeUpdateDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //공지 등록
    @PostMapping("/notice/{memberId}/{companyId}")
    public void noticeRegister(@PathVariable Long memberId, @PathVariable Long companyId,
                               @RequestBody RequestNoticeDto requestNoticeDto) throws IOException {
        noticeService.noticeRegister(memberId, companyId, requestNoticeDto);
    }

    //공지 수정
    @PatchMapping("/notice")
    public void noticeUpdate(@RequestBody RequestNoticeUpdateDto requestNoticeUpdateDto) throws IOException {
        noticeService.noticeUpdate(requestNoticeUpdateDto);
    }

    //공지사항 목록
    @GetMapping("/notice/{companyId}/{page}")
    public List<ResponseNoticeListDto> noticeList(@PathVariable Long companyId, @PathVariable int page) {

        Page<ResponseNoticeListDto> result = noticeService.noticeList(companyId, page);
        return result.getContent();
    }

    //공지사항 내용
    @GetMapping("/notice/{noticeId}")
    public ResponseNoticeDto noticeContent(@PathVariable Long noticeId) throws IOException {
        return noticeService.noticeContent(noticeId);
    }

    //공지사항 삭제
    @DeleteMapping("/notice/{noticeId}")
    public void removeNotice(@PathVariable Long noticeId) {
        noticeService.removeNotice(noticeId);
    }
}
