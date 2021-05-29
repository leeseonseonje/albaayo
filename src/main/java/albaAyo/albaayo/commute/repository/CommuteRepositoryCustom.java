package albaAyo.albaayo.commute.repository;

import albaAyo.albaayo.commute.Commute;

public interface CommuteRepositoryCustom {

    Commute offWork(Long workerId, Long companyId);
}
