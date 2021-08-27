package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.CompanyDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

    boolean existsByBusinessRegistrationNumber(String businessRegistrationNumber);

    @EntityGraph(attributePaths = {"member", "joinCompanies", "joinCompanies.member"})
    @Query("select c from Company c where c.id = :id")
    Optional<Company> findByCompanyMain(@Param("id") Long id);
}
