package albaAyo.albaayo.company.controller;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.*;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyMainDto;
import albaAyo.albaayo.company.service.CompanyService;
import albaAyo.albaayo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    //그룹 목록
    @GetMapping("/employer/{id}/company")
    public List<CompanyDto> companiesController(@PathVariable Long id) throws IOException {
        return companyService.companies(id);
    }

    //그룹 생성
    @PostMapping("/employer/{id}/company")
    public CompanyDto createCompanyController(@PathVariable Long id,
                                              @RequestBody @Valid RequestCreatCompanyDto requestCreatCompanyDto) throws IOException {
        Company company = companyService.EmployerCreateCompany(id, requestCreatCompanyDto);

        return new CompanyDto(company.getId(), company.getName(), company.getLocation(), company.getPicture());
    }

    //근로자 조회
    @GetMapping("/company/worker/{workerId}")
    public ResponseFindWorkerDto workerFind(@PathVariable String workerId) {
        return companyService.findWorker(workerId);
    }

    //그룹 메인
    @GetMapping("/company/{companyId}")
    public ResponseCompanyMainDto companyMain(@PathVariable("companyId") Long companyId) {
        return companyService.companyMain(companyId);
    }

    //근로자 초대
    @PostMapping("/company/{companyId}")
    public ResponseFindWorkerDto inviteWorker(@PathVariable("companyId") Long companyId,
                             @RequestBody @Valid RequestInviteWorkerDto request) {
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
}
