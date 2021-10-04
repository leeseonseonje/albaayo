package albaAyo.albaayo.domains.company.controller;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.*;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.domains.company.service.CompanyService;
import albaAyo.albaayo.domains.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    //그룹 목록
    @GetMapping("/employer/{memberId}/company")
    public List<CompanyDto> companiesController(@PathVariable Long memberId) throws IOException {
        List<Company> companies = companyService.companies(memberId);
        return companies.stream().map(CompanyDto::new)
                .collect(Collectors.toList());
    }

    //그룹 생성
    @PostMapping("/employer/{employerId}/company")
    public CompanyDto createCompanyController(@PathVariable Long employerId, @ModelAttribute RequestCompanyDto request) throws IOException {
        Company company = companyService.EmployerCreateCompany(employerId, request);

        return new CompanyDto(company.getId(), company.getName(), company.getLocation(), company.getPicture());
    }

    //근로자 조회
    @GetMapping("/company/worker/{workerUserId}")
    public ResponseFindWorkerDto workerFind(@PathVariable String workerUserId) {
        return companyService.findWorker(workerUserId);
    }

    //그룹 메인(소속회원 리스트)
    @GetMapping("{memberId}/company/{companyId}") // "/{memberId}/company/{companyId}"
    public Result<List<ResponseCompanyWorkerListDto>> companyMain(@PathVariable Long memberId,
                                                          @PathVariable Long companyId) {
        return companyService.companyMain(memberId, companyId);
    }

    //근로자 초대
    @PostMapping("/company/invite/{companyId}")
    public ResponseFindWorkerDto inviteWorker(@PathVariable("companyId") Long companyId,
                                              @RequestBody @Valid RequestInviteWorkerDto request) throws ExecutionException, InterruptedException {
        Member member = companyService.inviteWorker(companyId, request);

        return new ResponseFindWorkerDto(member.getUserId(), member.getName(), member.getBirth());
    }

    //멤버 상세 정보
    @GetMapping("/company/member/{memberId}")
    public ResponseMemberInformationDto memberInformation(@PathVariable("memberId") Long memberId) {

        Member findMember = companyService.memberInfo(memberId);

        return ResponseMemberInformationDto.builder()
                .name(findMember.getName())
                .userId(findMember.getUserId())
                .birth(findMember.getBirth())
                .role(findMember.getRole())
                .build();
    }

    //그룹 삭제
    @DeleteMapping("/company/{companyId}")
    public void removeCompany(@PathVariable Long companyId) {
        companyService.removeCompany(companyId);
    }

    //그룹 정보 수정
    @PatchMapping("/company/{companyId}")
    public void updateCompany(@PathVariable Long companyId, @ModelAttribute RequestCompanyDto request) throws IOException {

        companyService.updateCompany(companyId, request);
    }

}
