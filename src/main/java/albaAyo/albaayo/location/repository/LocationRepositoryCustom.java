package albaAyo.albaayo.location.repository;

import albaAyo.albaayo.location.Location;
import albaAyo.albaayo.location.dto.LocationDto;

public interface LocationRepositoryCustom {

    Location location(Long workerId, Long companyId);
}
