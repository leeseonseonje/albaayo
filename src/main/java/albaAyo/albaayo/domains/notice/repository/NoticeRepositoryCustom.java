package albaAyo.albaayo.domains.notice.repository;

import albaAyo.albaayo.domains.notice.dto.ResponseNoticeListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<ResponseNoticeListDto> noticeList(Long companyId, Pageable pageable);

}
