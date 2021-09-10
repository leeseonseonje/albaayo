package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyValidationService {

    private final CompanyRepository companyRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public void validateCompany(Company company) {

        if (companyRepository.existsByBusinessRegistrationNumber(company.getBusinessRegistrationNumber())) {
            throw new RuntimeException("이미 존재하는 회사입니다.");
        }
    }

    public Company memberRoleValidation(Long id, Member member) {
        if (member.getRole() == Role.ROLE_WORKER) {
            Company company = companyRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

            return validateDuplicateInvite(member, company);
        } else {
            throw new RuntimeException("근로자가 아닙니다.");
        }
    }

    public Company validateDuplicateInvite(Member member, Company company) {
        JoinCompany findJoinCompany = joinCompanyRepository.findJoinCompany(company.getId(), member.getId());
        if (findJoinCompany == null) {
            joinCompanyRepository.save(JoinCompany.builder()
                    .member(member)
                    .company(company)
                    .accept(Accept.NOT_ACCEPT)
                    .build());

            return company;
        } else {
            throw new RuntimeException("이미 초대한 근로자 입니다.");
        }
    }
}
