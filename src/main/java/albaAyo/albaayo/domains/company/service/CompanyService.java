package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.*;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final FcmService fcmService;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final CompanyImageService companyImageService;
    private final JoinCompanyRepository joinCompanyRepository;
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

    public Member inviteWorker(Long id, RequestInviteWorkerDto request) throws ExecutionException, InterruptedException {

        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        Company company = companyValidationService.memberRoleValidation(id, member);
        fcmNotification(member, company);
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

    private void fcmNotification(Member member, Company company) throws ExecutionException, InterruptedException {
        String fcmBody = company.getName() + "에서 초대가 왔습니다.";
        System.out.println("member.getFcmToken() = " + member.getFcmToken());
        fcmService.sendMessage(member.getFcmToken(), company.getName(), fcmBody);
    }

    public void updateCompany(Long companyId, RequestCompanyDto request) throws IOException {
        Company findCompany = companyRepository.findById(companyId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사입니다."));

        String url = companyImageService.imageUpload(request.getPicture());
        findCompany.updateCompany(request, url);
    }
}
