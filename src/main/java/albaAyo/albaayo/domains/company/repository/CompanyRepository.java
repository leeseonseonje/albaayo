package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

    boolean existsByBusinessRegistrationNumber(String businessRegistrationNumber);
}
