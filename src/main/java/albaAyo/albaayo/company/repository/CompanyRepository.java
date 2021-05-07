package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

    List<Company> findByBusinessRegistrationNumber(String businessRegistrationNumber);
}
