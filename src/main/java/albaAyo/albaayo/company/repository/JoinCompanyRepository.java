package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JoinCompanyRepository extends JpaRepository<JoinCompany, Long>, JoinCompanyRepositoryCustom {

    @Query("select j from JoinCompany j where j.company.id = :companyId and j.member.id = :workerId")
    JoinCompany findJoinCompany(@Param("companyId") Long companyId, @Param("workerId") Long workerId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from JoinCompany j where j.member.id = :workerId and j.company.id = :companyId")
    void joinCompanyDelete(@Param("workerId") Long workerId, @Param("companyId") Long companyId);

}
