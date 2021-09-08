package albaAyo.albaayo.domains.location.repository;

import albaAyo.albaayo.domains.location.Location;

public interface LocationRepositoryCustom {

    Location location(Long workerId, Long companyId);
}
