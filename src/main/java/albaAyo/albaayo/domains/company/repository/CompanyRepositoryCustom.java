package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<Company> findCompanies(Long id);

    List<ResponseCompanyWorkerListDto> findCompanyWorkerList(Long memberId, Long companyId);

    ResponseCompanyWorkerListDto findCompanyEmployer(Long companyId);

    Long test();
}
