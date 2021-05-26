package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.dto.RequestCompanyAcceptDto;
import albaAyo.albaayo.company.dto.Result;
import albaAyo.albaayo.company.service.WorkerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static albaAyo.albaayo.company.domain.Accept.*;

@RestController
@RequiredArgsConstructor
public class WorkerCompanyController {

    private final WorkerCompanyService workerCompanyService;

    // 수락하지 않은 그룹리스트
    @GetMapping("/worker/{workerId}/company/invite")
    public List<CompanyAcceptDto> notAcceptCompanyList(@PathVariable("workerId") Long workerId) {
        return workerCompanyService.acceptCompanyList(workerId, NOT_ACCEPT);
    }

    // 수락 버튼 눌렀을때
    @PostMapping("/worker/{workerId}/company/invite")
    public void acceptCompany(@PathVariable("workerId") Long workerId, @RequestBody RequestCompanyAcceptDto request) {
        workerCompanyService.acceptCompany(workerId, request.getId());
    }

    // 수락한 그룹 리스트
    @GetMapping("/worker/{workerId}/company")
    public Result<List<CompanyAcceptDto>> acceptCompanyList(@PathVariable("workerId") Long workerId) {
        List<CompanyAcceptDto> companies = workerCompanyService.acceptCompanyList(workerId, ACCEPT);
        Long count = workerCompanyService.notAcceptCompanyCount(workerId);

        return new Result<>(companies, count);
    }
}
