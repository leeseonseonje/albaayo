package albaAyo.albaayo.domains.location.repository;

import albaAyo.albaayo.domains.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long>, LocationRepositoryCustom {

    @Query("select l from Location l where l.member.id = :memberId")
    Location findByMemberId(@Param("memberId") Long memberId);
}
