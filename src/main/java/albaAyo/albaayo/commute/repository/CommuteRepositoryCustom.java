package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;

import java.util.List;

public interface CommuteRepositoryCustom {

    Commute commute(Long workerId, Long companyId);

    List<Commute> commuteList(Long workerId, Long companyId);
}
