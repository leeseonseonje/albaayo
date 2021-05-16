package albaAyo.albaayo.company.repository;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.CompanyAcceptDto;

import java.util.List;

public interface JoinCompanyRepositoryCustom {

    List<CompanyAcceptDto> CompanyAcceptList(Long workerId, Accept accept);
}
