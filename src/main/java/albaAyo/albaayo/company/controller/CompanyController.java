package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.*;
import albaAyo.albaayo.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    //그룹 목록
    @GetMapping("/employer/{id}/company")
    public List<CompanyDto> companiesController(@PathVariable Long id) {
        return companyService.companies(id);
    }

    //그룹 생성
    @PostMapping("/employer/{id}/company")
    public CompanyDto createCompanyController(@PathVariable Long id,
                                              @RequestBody RequestCreatCompanyDto requestCreatCompanyDto) {

        Company company = Company.builder()
                .name(requestCreatCompanyDto.getName())
                .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                .location(requestCreatCompanyDto.getLocation())
                .build();

        Company savedCompany = companyService.EmployerCreateCompany(id, company);

        return new CompanyDto(savedCompany.getId(), savedCompany.getName(), savedCompany.getLocation());
    }

    //근로자 조회
    @GetMapping("/company/worker")
    public ResponseFindWorkerDto workerFind(String workerId) {
        return companyService.findWorker(workerId);
    }

    //그룹 메인
    @GetMapping("/company/{companyId}")
    public ResponseCompanyMainDto companyMain(@PathVariable("companyId") Long companyId) {
        return companyService.companyMain(companyId);
    }

    //근로자 초대
    @PostMapping("/company/{companyId}")
    public void inviteWorker(@PathVariable("companyId") Long companyId,
                             @RequestBody @Valid RequestInviteWorkerDto request) {
        companyService.inviteWorker(companyId, request);
    }
}
