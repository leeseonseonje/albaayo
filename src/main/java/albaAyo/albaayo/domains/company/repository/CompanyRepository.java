package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

    boolean existsByBusinessRegistrationNumber(String businessRegistrationNumber);
}
