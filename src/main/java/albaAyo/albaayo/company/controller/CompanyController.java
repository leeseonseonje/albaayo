package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.company.dto.RequestCreatCompanyDto;
import albaAyo.albaayo.company.dto.RequestInviteWorkerDto;
import albaAyo.albaayo.company.dto.ResponseCreatCompanyDto;
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

    @GetMapping("/employer/{id}/company")
    public List<ResponseCreatCompanyDto> companiesController(@PathVariable Long id) {
        List<Company> companies = companyService.companies(id);

        return companies.stream().map(c -> new ResponseCreatCompanyDto(c.getId(), c.getName(), c.getLocation()))
                .collect(Collectors.toList());
    }

    @PostMapping("/employer/{id}/company")
    public ResponseCreatCompanyDto createCompanyController(@PathVariable Long id,
                                                           @RequestBody RequestCreatCompanyDto requestCreatCompanyDto) {

        Company company = Company.builder()
                .name(requestCreatCompanyDto.getName())
                .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                .location(requestCreatCompanyDto.getLocation())
                .build();

        Company savedCompany = companyService.EmployerCreateCompany(id, company);

        return new ResponseCreatCompanyDto(savedCompany.getId(), savedCompany.getName(), savedCompany.getLocation());
    }

    @PostMapping("/employer/{employerId}/company/{companyId}")
    public void inviteWorker(@PathVariable("employerId") Long employerId, @PathVariable("companyId") Long companyId,
                             @RequestBody @Valid RequestInviteWorkerDto request) {


    }
}
