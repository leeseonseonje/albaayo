package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.service.WorkerCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static albaAyo.albaayo.company.domain.Accept.*;

@RestController
@RequiredArgsConstructor
public class WorkerCompanyController {

    private final WorkerCompanyService workerCompanyService;

    @GetMapping("/worker/{workerId}/company/invite")
    public List<CompanyAcceptDto> notAcceptCompany(@PathVariable("workerId") Long workerId) {
        return workerCompanyService.acceptCompany(workerId, NOT_ACCEPT);
    }

    @GetMapping("/worker/{workerId}/company")
    public List<CompanyAcceptDto> acceptCompany(@PathVariable("workerId") Long workerId) {
        return workerCompanyService.acceptCompany(workerId, ACCEPT);
    }
}
