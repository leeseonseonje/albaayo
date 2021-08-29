package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyValidationService {

    private final CompanyRepository companyRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public void validateCompany(Company company) {

        if (companyRepository.existsByBusinessRegistrationNumber(company.getBusinessRegistrationNumber())) {
            throw new RuntimeException("이미 존재하는 회사입니다.");
        }
    }

    public void memberRoleValidation(Long id, Member member) {
        if (member.getRole() == Role.ROLE_WORKER) {
            Company company = companyRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

            validateDuplicateInvite(member, company);
        } else {
            throw new RuntimeException("근로자가 아닙니다.");
        }
    }

    public void validateDuplicateInvite(Member member, Company company) {
        JoinCompany findJoinCompany = joinCompanyRepository.findJoinCompany(company.getId(), member.getId());
        if (findJoinCompany == null) {
            joinCompanyRepository.save(JoinCompany.builder()
                    .member(member)
                    .company(company)
                    .accept(Accept.NOT_ACCEPT)
                    .build());
        } else {
            throw new RuntimeException("이미 초대한 근로자 입니다.");
        }
    }
}
