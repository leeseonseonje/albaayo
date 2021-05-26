package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.domain.JoinCompany;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;

import java.util.List;

public interface JoinCompanyRepositoryCustom {

    List<CompanyAcceptDto> acceptCompanyList(Long workerId, Accept accept);

    Long notAcceptCompanyCount(Long workerId);

    void companyAccept(Long workerId, Long companyId);


}
