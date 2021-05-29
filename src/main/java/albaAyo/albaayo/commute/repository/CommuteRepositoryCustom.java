package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;

import java.util.List;

public interface CommuteRepositoryCustom {

    Commute commute(Long workerId, Long companyId);

    List<ResponseCommuteListDto> commuteList(Long workerId, Long companyId);
}
