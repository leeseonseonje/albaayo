package albaAyo.albaayo.location.repository;

import albaAyo.albaayo.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
