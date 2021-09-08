package albaAyo.albaayo.domains.notice.repository;

import albaAyo.albaayo.domains.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
}
