package albaAyo.albaayo.notice.repository;

import albaAyo.albaayo.notice.domain.NoticeImage;
import albaAyo.albaayo.notice.dto.NoticeImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeImageRepository extends JpaRepository<NoticeImage, Long> {

    @Query("select new albaAyo.albaayo.notice.dto.NoticeImageDto(n.image, n.imageContent) from NoticeImage n where n.notice.id = :noticeId")
    List<NoticeImageDto> findNoticeImage(@Param("noticeId") Long noticeId);
}
