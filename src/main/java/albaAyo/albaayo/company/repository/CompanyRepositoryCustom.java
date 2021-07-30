package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.CompanyDto;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyWorkerListDto;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<CompanyDto> findCompanies(Long id);

    List<ResponseCompanyWorkerListDto> findCompanyWorkerList(Long companyId);

    ResponseCompanyWorkerListDto findCompanyEmployer(Long companyId);
}
