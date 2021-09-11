package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<CompanyDto> findCompanies(Long id);

    List<ResponseCompanyWorkerListDto> findCompanyWorkerList(Long companyId);

    ResponseCompanyWorkerListDto findCompanyEmployer(Long companyId);
}