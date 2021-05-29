package albaAyo.albaayo.schedule.controller;

import albaAyo.albaayo.schedule.domain.Schedule;
import albaAyo.albaayo.schedule.dto.*;
import albaAyo.albaayo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    ScheduleService scheduleService;

    //일정 등록
    @PostMapping("/schedule")
    public void registerSchedule(@RequestBody RequestScheduleDto request) {
        scheduleService.registerSchedule(request);
    }

    //일정 수정
    @PatchMapping("/schedule")
    public void updateSchedule(@RequestBody RequestUpdateScheduleDto request) {
        scheduleService.updateSchedule(request);
    }

    //일정 삭제
    @DeleteMapping("/schedule")
    public void deleteSchedule(@RequestBody RequestDeleteScheduleDto request) {
        scheduleService.deleteSchedule(request);
    }

    //일정 전체 조회
    @GetMapping("/schedule/{companyId}/{date}")
    public List<ResponseScheduleListDto> scheduleList(@PathVariable Long companyId, @PathVariable Integer date) {
        return scheduleService.scheduleList(companyId, date);
    }

    //일정 단건 조회(상세)
    @GetMapping("/schedule/{scheduleId}")
    public ResponseScheduleDto schedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.schedule(scheduleId);

        return new ResponseScheduleDto(schedule.getId(), schedule.getWorkSchedule());
    }
}
