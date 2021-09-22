package albaAyo.albaayo.domains.company.controller;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.dto.Result;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.company.service.WorkerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static albaAyo.albaayo.domains.company.domain.Accept.*;

@RestController
@RequiredArgsConstructor
public class WorkerCompanyController {

    private final WorkerCompanyService workerCompanyService;

    //수락하지 않은 그룹리스트
    @GetMapping("/worker/{workerId}/company/invite") // /worker/{workerId}/invite
    public List<CompanyDto> notAcceptCompanyList(@PathVariable Long workerId) throws IOException {

        return workerCompanyService.notAcceptCompanyList(workerId, NOT_ACCEPT)
                .stream().map(CompanyDto::new).collect(Collectors.toList());
    }

    //초대 수락
    @PostMapping("/worker/{workerId}/{companyId}/invite")
    public void acceptCompany(@PathVariable Long workerId, @PathVariable Long companyId)
            throws ExecutionException, InterruptedException {
        workerCompanyService.acceptCompany(workerId, companyId);
    }

    //초대 거절
    @DeleteMapping("/worker/{workerId}/{companyId}/invite")
    public void notAcceptCompany(@PathVariable Long workerId, @PathVariable Long companyId)
            throws ExecutionException, InterruptedException {
        workerCompanyService.notAcceptCompany(workerId, companyId);
    }

    //수락한 그룹 리스트
    @GetMapping("/worker/{workerId}/company")
    public Result<List<CompanyDto>> acceptCompanyList(@PathVariable("workerId") Long workerId) throws IOException {

        List<JoinCompany> companies = workerCompanyService.acceptCompanyList(workerId, ACCEPT);
        List<CompanyDto> result = companies.stream().map(CompanyDto::new)
                .collect(Collectors.toList());
        Long count = workerCompanyService.notAcceptCompanyCount(workerId);

        return new Result<>(result, count);
    }

    //그룹 퇴장
    @DeleteMapping("/company/{workerId}/{companyId}")
    public void companyExit(@PathVariable Long workerId, @PathVariable Long companyId)
            throws ExecutionException, InterruptedException {
        workerCompanyService.companyExit(workerId, companyId);
    }
}
