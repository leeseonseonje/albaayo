package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;
import albaAyo.albaayo.company.repository.JoinCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerCompanyService {

    private final JoinCompanyRepository joinCompanyRepository;

    public List<CompanyAcceptDto> acceptCompany(Long workerId, Accept accept) {
        return joinCompanyRepository.CompanyAcceptList(workerId, accept);
    }
}
