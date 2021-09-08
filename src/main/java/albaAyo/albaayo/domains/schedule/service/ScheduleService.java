package albaAyo.albaayo.domains.schedule.service;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.schedule.domain.Schedule;
import albaAyo.albaayo.domains.schedule.dto.RequestScheduleDto;
import albaAyo.albaayo.domains.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    public void registerSchedule(RequestScheduleDto request) {

        Company company = companyRepository.findById(request.getCompanyId()).orElseGet(Company::new);

        Schedule findSchedule = scheduleRepository.findByDate(request.getCompanyId(), request.getDate());
        if (findSchedule != null) {
            findSchedule.updateSchedule(request.getWorkSchedule());

        } else {
            Schedule schedule = Schedule.builder().company(company).workSchedule(request.getWorkSchedule())
                    .date(request.getDate()).build();

            scheduleRepository.save(schedule);
        }
    }

//    public void updateSchedule(RequestUpdateScheduleDto request) {
//
//        Schedule schedule = scheduleRepository.findById(request.getId()).orElseGet(Schedule::new);
//
//        schedule.updateSchedule(request.getWorkSchedule(), request.getDate());
//    }
//
//    public void deleteSchedule(RequestDeleteScheduleDto request) {
//        Schedule schedule = scheduleRepository.findByDate(request.getDate());
//
//        scheduleRepository.delete(schedule);
//    }

//    public List<ResponseScheduleListDto> scheduleList(Long companyId, Integer date) {
//        return scheduleRepository.scheduleList(companyId, date);
//    }

    public Schedule schedule(Long companyId, String date) {
        return scheduleRepository.findByDate(companyId, date);
    }
}
