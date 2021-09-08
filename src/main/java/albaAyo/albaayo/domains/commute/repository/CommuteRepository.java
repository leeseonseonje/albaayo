package albaAyo.albaayo.domains.commute.repository;

import albaAyo.albaayo.domains.commute.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteRepository extends JpaRepository<Commute, Long>, CommuteRepositoryCustom {
}
