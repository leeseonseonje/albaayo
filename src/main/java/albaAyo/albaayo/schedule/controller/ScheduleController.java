package albaAyo.albaayo.schedule.controller;

import albaAyo.albaayo.schedule.domain.Schedule;
import albaAyo.albaayo.schedule.dto.*;
import albaAyo.albaayo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 등록
    @PostMapping("/schedule")
    public void registerSchedule(@RequestBody @Valid RequestScheduleDto request) {
        scheduleService.registerSchedule(request);
    }

//    //일정 수정
//    @PatchMapping("/schedule")
//    public void updateSchedule(@RequestBody @Valid RequestUpdateScheduleDto request) {
//        scheduleService.updateSchedule(request);
//    }

//    //일정 삭제
//    @DeleteMapping("/schedule")
//    public void deleteSchedule(@RequestBody RequestDeleteScheduleDto request) {
//        scheduleService.deleteSchedule(request);
//    }

//    //일정 전체 조회
//    @GetMapping("/schedule/{companyId}/{date}")
//    public List<ResponseScheduleListDto> scheduleList(@PathVariable Long companyId, @PathVariable Integer date) {
//        return scheduleService.scheduleList(companyId, date);
//    }

    //일정 단건 조회(상세)
    @GetMapping("/schedule/{companyId}/{date}")
    public ResponseScheduleDto schedule(@PathVariable Long companyId, @PathVariable String date) {
        Schedule schedule = scheduleService.schedule(companyId, date);

        if (schedule != null) {
            return new ResponseScheduleDto(schedule.getId(), schedule.getWorkSchedule());
        } else {
            return new ResponseScheduleDto(1L, "");
        }
    }
}
