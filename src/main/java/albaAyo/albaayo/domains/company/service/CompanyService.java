package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.chat.repository.ChatRepository;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.dto.*;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    @Value("${path.company}")
    private String path;

    private final FcmService fcmService;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public Company EmployerCreateCompany(Long employerId, RequestCompanyDto requestCreatCompanyDto) throws IOException {

        Company company = companyBuilder(requestCreatCompanyDto);

        validateCompany(company);

        Member member =  memberRepository.findById(employerId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원 입니다."));

        company.employerCreateCompany(member);
        return companyRepository.save(company);
    }

    public Company companyBuilder(RequestCompanyDto request) throws IOException {
        Company company = Company.builder()
                .name(request.getName())
                .businessRegistrationNumber(request.getBusinessRegistrationNumber())
                .location(request.getLocation())
                .build();

        if (request.getPicture() == null) {
            return company;
        }
        else if (!request.getPicture().isEmpty()) {
            company.companyPictureSetting(company.imageUpload(request.getPicture(), path));
        }
        return company;
    }

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

    public List<Company> companies(Long id) throws IOException {
        List<Company> companies = companyRepository.findCompanies(id);
        for (Company company : companies) {
            company.imageDownload(company);
        }
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

    public Result<List<ResponseCompanyWorkerListDto>> companyMain(Long memberId, Long companyId) {
        List<ResponseCompanyWorkerListDto> workerList = companyRepository.findCompanyWorkerList(memberId, companyId);
        if (workerList.isEmpty()) {
            workerList.add(companyRepository.findCompanyEmployer(companyId));
        }
        Long count = chatRepository.companyChatHistoryCount(companyId);
        return new Result<>(workerList, count);
    }

    public Member inviteWorker(Long id, RequestInviteWorkerDto request) throws ExecutionException, InterruptedException {

        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        Company company = memberRoleValidation(id, member);
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
        fcmService.sendMessage(member.getFcmToken(), company.getName(), fcmBody);
    }

    public void updateCompany(Long companyId, RequestCompanyDto request) throws IOException {
        Company findCompany = companyRepository.findById(companyId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사입니다."));

        String picture = findCompany.imageUpload(request.getPicture(), path);
        findCompany.updateCompany(request, picture);
        findCompany.imageDelete(path);
    }
}
