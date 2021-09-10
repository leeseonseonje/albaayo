package albaAyo.albaayo.domains.schedule.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.schedule.domain.Schedule;
import albaAyo.albaayo.domains.schedule.dto.RequestScheduleDto;
import albaAyo.albaayo.domains.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final FcmService fcmService;
    private final CompanyRepository companyRepository;
    private final ScheduleRepository scheduleRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public void registerSchedule(RequestScheduleDto request) throws ExecutionException, InterruptedException {

        Company company = companyRepository.findById(request.getCompanyId()).orElseGet(Company::new);

        Schedule findSchedule = scheduleRepository.findByDate(request.getCompanyId(), request.getDate());
        if (findSchedule != null) {
            findSchedule.updateSchedule(request.getWorkSchedule());

        } else {
            Schedule schedule = Schedule.builder().company(company).workSchedule(request.getWorkSchedule())
                    .date(request.getDate()).build();

            scheduleRepository.save(schedule);
            fcmNotification(company, request);
        }
    }

    private void fcmNotification(Company company, RequestScheduleDto request) throws ExecutionException, InterruptedException {

        List<String> fcmTokens = joinCompanyRepository.companyWorkers(company.getId(), request.getMemberId());
        if (!company.getMember().getId().equals(request.getMemberId())) {
            fcmTokens.add(company.getMember().getFcmToken());
        }

        String fcmBody = request.getDate() + "에 일정이 등록되었습니다.";
        fcmService.sendMulticastMessage(fcmTokens, company.getName(), fcmBody);

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
