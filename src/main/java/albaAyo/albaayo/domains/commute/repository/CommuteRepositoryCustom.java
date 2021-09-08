package albaAyo.albaayo.domains.commute.repository;

import albaAyo.albaayo.domains.commute.Commute;

import java.time.LocalDateTime;
import java.util.List;

public interface CommuteRepositoryCustom {

    Commute commute(Long workerId, Long companyId);

    List<Commute> commuteList(Long workerId, Long companyId);

    List<Commute> monthCommuteList(Long workerId, Long companyId, LocalDateTime date);
}
