package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.*;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final CompanyImageService companyImageService;
    private final CompanyValidationService companyValidationService;

    public Company EmployerCreateCompany(Long id, RequestCompanyDto requestCreatCompanyDto) throws IOException {

        Company company = companyImageService.companyBuilder(requestCreatCompanyDto);

        companyValidationService.validateCompany(company);

        Member member =  memberRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원 입니다."));

        company.employerCreateCompany(member);
        return companyRepository.save(company);
    }

    public List<CompanyDto> companies(Long id) throws IOException {
        List<CompanyDto> companies = companyRepository.findCompanies(id);
        companyImageService.imageDownload(companies);
        return companies;
    }

    public ResponseFindWorkerDto findWorker(String workerId) {
        Member member = memberRepository.findByUserId(workerId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        if (member.getRole() == Role.ROLE_WORKER) {
            return new ResponseFindWorkerDto(member.getUserId(), member.getName(), member.getBirth());
        } else {
            throw new RuntimeException("근로자가 아닙니다.");
        }
    }

    public List<ResponseCompanyWorkerListDto> companyMain(Long companyId) {
        List<ResponseCompanyWorkerListDto> workerList = companyRepository.findCompanyWorkerList(companyId);
        if (workerList.isEmpty()) {
            workerList.add(companyRepository.findCompanyEmployer(companyId));
        }
        return workerList;
    }

    public Member inviteWorker(Long id, RequestInviteWorkerDto request) {

        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        companyValidationService.memberRoleValidation(id, member);
        return member;
    }

    public Member memberInfo(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재 하지 않는 회원 입니다."));
    }

    public void removeCompany(Long companyId) {
        Company findCompany = companyRepository.findById(companyId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사 입니다."));

        companyRepository.delete(findCompany);
    }

    public void updateCompany(Long companyId, RequestCompanyDto request) throws IOException {
        Company findCompany = companyRepository.findById(companyId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사입니다."));

        String url = companyImageService.imageUpload(request);
        findCompany.updateCompany(request, url);
    }
}
