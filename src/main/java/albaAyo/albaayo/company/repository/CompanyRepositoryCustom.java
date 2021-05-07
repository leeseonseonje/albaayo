package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.Company;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<Company> findCompanies(Long id);
}
