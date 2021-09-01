package albaAyo.albaayo.commute.controller;

import albaAyo.albaayo.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.commute.dto.RequestUpdateCommuteDto;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;
import albaAyo.albaayo.commute.dto.ResponsePayInformationDto;
import albaAyo.albaayo.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommuteController {

    private final CommuteService commuteService;

    //출근
    @PostMapping("/commute/go-to-work")
    public void goToWork(@RequestBody RequestCommuteDto request) {
        commuteService.goToWork(request);
    }

    //퇴근
    @PostMapping("/commute/off-work")
    public void offWork(@RequestBody RequestCommuteDto request) {
        commuteService.offWork(request);
    }

    //출근 시간 수정
    @PatchMapping("/commute/go-to-work")
    public void updateGotoWork(@RequestBody @Valid RequestUpdateCommuteDto request) {
        commuteService.updateGoToWork(request);
    }

    //퇴근 시간 수정
    @PatchMapping("/commute/off-work")
    public void updateOffWork(@RequestBody @Valid RequestUpdateCommuteDto request) {
        commuteService.updateOffWork(request);
    }

    //그룹별 출근 내역 조회
    @GetMapping("/commute/{workerId}/{companyId}")
    public List<ResponseCommuteListDto> commuteList(@PathVariable("workerId") Long workerId,
                                                    @PathVariable("companyId") Long companyId) {
        return commuteService.commuteList(workerId, companyId);
    }

    //급여 조회(월별)
    @GetMapping("/pay/{workerId}/{companyId}")
    public ResponsePayInformationDto monthPayInfo(@PathVariable Long workerId, @PathVariable Long companyId,
                                                      @RequestParam("date") String date) {
        return commuteService.monthPayInfo(workerId, companyId, date);
    }
}
