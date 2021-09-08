package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerCompanyService {

    private final JoinCompanyRepository joinCompanyRepository;
    private final CompanyImageService companyImageService;

    public List<CompanyDto> acceptCompanyList(Long workerId, Accept accept) throws IOException {
        List<CompanyDto> companies = joinCompanyRepository.acceptCompanyList(workerId, accept);
        companyImageService.imageDownload(companies);
        return companies;
    }

    public Long notAcceptCompanyCount(Long workerId) {
        return joinCompanyRepository.notAcceptCompanyCount(workerId);
    }

    public void acceptCompany(Long workerId, Long companyId) {
        joinCompanyRepository.companyAccept(workerId, companyId);
    }

    public void notAcceptCompany(Long workerId, Long companyId) {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
    }

    public List<CompanyDto> notAcceptCompanyList(Long workerId, Accept accept) {
        return joinCompanyRepository.acceptCompanyList(workerId, accept);
    }

    //그룹 퇴장
    public void companyExit(Long workerId, Long companyId) {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
    }
}
