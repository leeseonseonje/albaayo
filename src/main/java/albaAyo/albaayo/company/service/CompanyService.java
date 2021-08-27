package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.*;
import albaAyo.albaayo.company.dto.company_main_dto.IdAndName;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyMainDto;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;
    private final JoinCompanyRepository joinCompanyRepository;
    private final CompanyFileService companyFileService;

    public Company EmployerCreateCompany(Long id, RequestCompanyDto requestCreatCompanyDto) throws IOException {

        Company company = companyFileService.companyBuilder(requestCreatCompanyDto);

        validateCompany(company);
        Member member =  memberRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원 입니다."));
        company.employerCreateCompany(member);

        return companyRepository.save(company);
    }

    private void validateCompany(Company company) {

        if (companyRepository.existsByBusinessRegistrationNumber(company.getBusinessRegistrationNumber())) {
            throw new RuntimeException("이미 존재하는 회사입니다.");
        }
    }

    public List<CompanyDto> companies(Long id) throws IOException {
        List<CompanyDto> companies = companyRepository.findCompanies(id);
        companyFileService.imageDownload(companies);
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

        if (member.getRole() == Role.ROLE_WORKER) {
            Company company = companyRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

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
        } else {
            throw new RuntimeException("근로자가 아닙니다.");
        }
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

        UUID uuid = companyFileService.imageUpload(request);
        request.setPicture("C:\\Users\\seon\\groupImage\\" + uuid.toString() + ".jpeg");

        findCompany.updateCompany(request);
    }
}























//    private void imageDownload(List<CompanyDto> companies) throws IOException {
//        for (CompanyDto company : companies) {
//            if (company.getPicture() != null) {
//                Path path = Paths.get(company.getPicture());
//                Resource resource = new InputStreamResource(Files.newInputStream(path));
//                InputStream inputStream = resource.getInputStream();
//                byte[] bytes = inputStream.readAllBytes();
//                String picture = Base64.encodeBase64String(bytes);
//                company.setPicture(picture);
//            }
//        }
//    }


//    public Company companyBuilder(RequestCompanyDto requestCreatCompanyDto) throws IOException {
//        Company company;
//        if (requestCreatCompanyDto.getPicture() != null) {
//            UUID uuid = imageUpload(requestCreatCompanyDto);
//
//            company = Company.builder()
//                    .name(requestCreatCompanyDto.getName())
//                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
//                    .location(requestCreatCompanyDto.getLocation())
//                    .picture("C:\\Users\\seon\\groupImage\\" + uuid.toString() + ".jpeg")
//                    .build();
//        } else {
//            company = Company.builder()
//                    .name(requestCreatCompanyDto.getName())
//                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
//                    .location(requestCreatCompanyDto.getLocation())
//                    .build();
//        }
//        return company;
//    }
//
//    private UUID imageUpload(RequestCompanyDto requestCreatCompanyDto) throws IOException {
//        byte[] bytes = Base64.decodeBase64(requestCreatCompanyDto.getPicture());
//        UUID uuid = UUID.randomUUID();
//        FileImageOutputStream image = new FileImageOutputStream(
//                new File("C:\\Users\\seon\\groupImage\\" + uuid.toString() + ".jpeg"));
//        image.write(bytes, 0, bytes.length);
//        image.close();
//        return uuid;
//    }
//
//    private void validateCompany(Company company) {
//
//        List<Company> findCompany = companyRepository.findByBusinessRegistrationNumber(
//                company.getBusinessRegistrationNumber());
//        if (!findCompany.isEmpty()) {
//            throw new RuntimeException("이미 존재하는 회사입니다.");
//        }
//    }
