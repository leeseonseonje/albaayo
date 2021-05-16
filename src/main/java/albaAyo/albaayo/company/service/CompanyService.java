package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.company.JoinCompany;
import albaAyo.albaayo.company.dto.RequestInviteWorkerDto;
import albaAyo.albaayo.company.dto.ResponseFindWorkerDto;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public Company EmployerCreateCompany(Long id, Company company) {

        validateCompany(company);
        Company savedCompany = companyRepository.save(company);
        Member member =  memberRepository.findById(id).orElseGet(Member::new);
        savedCompany.employerCreateCompany(member);

        return savedCompany;
    }

    private void validateCompany(Company company) {
        List<Company> findCompany = companyRepository.findByBusinessRegistrationNumber(company.getBusinessRegistrationNumber());
        if (!findCompany.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회사입니다.");
        }
    }

    public List<Company> companies(Long id) {
        return companyRepository.findCompanies(id);
    }

    public ResponseFindWorkerDto findWorker(String workerId) {
        Member member = memberRepository.findByUserId(workerId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        return new ResponseFindWorkerDto(member.getUserId(), member.getName(), member.getBirth());
    }

    public void inviteWorker(Long id, RequestInviteWorkerDto request) {
        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        if (member.getRole() == Role.ROLE_WORKER) {
            Company company = companyRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 Company 입니다."));

            joinCompanyRepository.save(JoinCompany.builder()
                    .member(member)
                    .company(company)
                    .build());
        }

    }
}
