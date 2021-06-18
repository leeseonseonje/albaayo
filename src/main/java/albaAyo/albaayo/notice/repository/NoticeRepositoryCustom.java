package albaAyo.albaayo.notice.repository;

import albaAyo.albaayo.notice.dto.ResponseNoticeDto;
import albaAyo.albaayo.notice.dto.ResponseNoticeListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoticeRepositoryCustom {

    Page<ResponseNoticeListDto> noticeList(Long companyId, Pageable pageable);

    ResponseNoticeDto noticeContent(Long noticeId);
}
