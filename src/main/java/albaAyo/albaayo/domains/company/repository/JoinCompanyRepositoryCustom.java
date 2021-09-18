package albaAyo.albaayo.domains.company.repository;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;

import java.util.List;

public interface JoinCompanyRepositoryCustom {

    List<JoinCompany> acceptCompanyList(Long workerId, Accept accept);

    Long notAcceptCompanyCount(Long workerId);

    void companyAccept(Long workerId, Long companyId);

    List<String> companyWorkers(Long companyId, Long memberId);
}
