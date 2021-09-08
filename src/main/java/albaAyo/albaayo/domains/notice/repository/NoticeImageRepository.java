package albaAyo.albaayo.domains.notice.repository;

import albaAyo.albaayo.domains.notice.domain.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeImageRepository extends JpaRepository<NoticeImage, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from NoticeImage n where n.notice.id = :noticeId")
    void noticeImageDelete(@Param("noticeId") Long noticeId);
}
