package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.dto.CompanyDto;
import albaAyo.albaayo.company.dto.Result;
import albaAyo.albaayo.company.service.WorkerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static albaAyo.albaayo.company.domain.Accept.*;

@RestController
@RequiredArgsConstructor
public class WorkerCompanyController {

    private final WorkerCompanyService workerCompanyService;

    //수락하지 않은 그룹리스트
    @GetMapping("/worker/{workerId}/company/invite")
    public List<CompanyDto> notAcceptCompanyList(@PathVariable Long workerId) throws IOException {
        return workerCompanyService.notAcceptCompanyList(workerId, NOT_ACCEPT);
    }

    //초대 수락
    @PostMapping("/worker/{workerId}/{companyId}/invite")
    public void acceptCompany(@PathVariable Long workerId, @PathVariable Long companyId) {
        workerCompanyService.acceptCompany(workerId, companyId);
    }

    //초대 거절
    @DeleteMapping("/worker/{workerId}/{companyId}/invite")
    public void notAcceptCompany(@PathVariable() Long workerId, @PathVariable Long companyId) {
        workerCompanyService.notAcceptCompany(workerId, companyId);
    }

    //수락한 그룹 리스트
    @GetMapping("/worker/{workerId}/company")
    public Result<List<CompanyDto>> acceptCompanyList(@PathVariable("workerId") Long workerId) throws IOException {

        List<CompanyDto> companies = workerCompanyService.acceptCompanyList(workerId, ACCEPT);
        Long count = workerCompanyService.notAcceptCompanyCount(workerId);

        return new Result<>(companies, count);
    }

    //그룹 퇴장
    @DeleteMapping("/company/{workerId}/{companyId}")
    public void companyExit(@PathVariable Long workerId, @PathVariable Long companyId) {
        workerCompanyService.companyExit(workerId, companyId);
    }
}
