package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.CompanyDto;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<CompanyDto> findCompanies(Long id);
}
