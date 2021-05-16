package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinCompanyRepository extends JpaRepository<JoinCompany, Long>, JoinCompanyRepositoryCustom {


}
