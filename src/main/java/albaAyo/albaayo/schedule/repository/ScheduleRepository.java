package albaAyo.albaayo.schedule.repository;

import albaAyo.albaayo.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select s from Schedule s where s.date = :date and s.company.id = :companyId")
    Schedule findByDate(@Param("companyId") Long companyId, @Param("date") String date);
}
