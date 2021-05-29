package albaAyo.albaayo.schedule.service;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.schedule.domain.Schedule;
import albaAyo.albaayo.schedule.dto.RequestDeleteScheduleDto;
import albaAyo.albaayo.schedule.dto.RequestScheduleDto;
import albaAyo.albaayo.schedule.dto.RequestUpdateScheduleDto;
import albaAyo.albaayo.schedule.dto.ResponseScheduleListDto;
import albaAyo.albaayo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;

    public void registerSchedule(RequestScheduleDto request) {

        Company company = companyRepository.findById(request.getId()).orElseGet(Company::new);

        Schedule schedule = Schedule.builder().company(company).workSchedule(request.getWorkSchedule())
                .date(request.getDate()).build();

        scheduleRepository.save(schedule);
    }

    public void updateSchedule(RequestUpdateScheduleDto request) {

        Schedule schedule = scheduleRepository.findById(request.getId()).orElseGet(Schedule::new);

        schedule.updateSchedule(request.getWorkSchedule(), request.getDate());
    }

    public void deleteSchedule(RequestDeleteScheduleDto request) {
        Schedule schedule = scheduleRepository.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("null"));

        scheduleRepository.delete(schedule);
    }

    public List<ResponseScheduleListDto> scheduleList(Long companyId, Integer date) {
        return scheduleRepository.scheduleList(companyId, date);
    }

    public Schedule schedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseGet(Schedule::new);
    }
}
