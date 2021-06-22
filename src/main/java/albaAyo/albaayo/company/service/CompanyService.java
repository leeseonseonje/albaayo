package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.*;
import albaAyo.albaayo.company.dto.company_main_dto.IdAndName;
import albaAyo.albaayo.company.dto.company_main_dto.ResponseCompanyMainDto;
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

    public Company EmployerCreateCompany(Long id, RequestCreatCompanyDto requestCreatCompanyDto) throws IOException {

        Company company = null;
        if (requestCreatCompanyDto.getPicture() != null) {
            UUID uuid = imageUpload(requestCreatCompanyDto);

            company = Company.builder()
                    .name(requestCreatCompanyDto.getName())
                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                    .location(requestCreatCompanyDto.getLocation())
                    .picture("/home/ec2-user/groupImage" + uuid.toString() + ".jpg")
                    .build();
        } else {
            company = Company.builder()
                    .name(requestCreatCompanyDto.getName())
                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                    .location(requestCreatCompanyDto.getLocation())
                    .build();
        }

        validateCompany(company);
        Member member =  memberRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원 입니다."));
        company.employerCreateCompany(member);

        return companyRepository.save(company);
    }

    private UUID imageUpload(RequestCreatCompanyDto requestCreatCompanyDto) throws IOException {
        byte[] bytes = Base64.decodeBase64(requestCreatCompanyDto.getPicture());
        UUID uuid = UUID.randomUUID();
        FileImageOutputStream image = new FileImageOutputStream(
                new File("/home/ec2-user/groupImage" + uuid.toString() + ".jpg"));
        image.write(bytes, 0, bytes.length);
        image.close();
        return uuid;
    }

    private void validateCompany(Company company) {

        List<Company> findCompany = companyRepository.findByBusinessRegistrationNumber(
                company.getBusinessRegistrationNumber());
        if (!findCompany.isEmpty()) {
            throw new RuntimeException("이미 존재하는 회사입니다.");
        }
    }

    public List<CompanyDto> companies(Long id) throws IOException {
        List<CompanyDto> companies = companyRepository.findCompanies(id);
        imageDownload(companies);
        return companies;
    }

    private void imageDownload(List<CompanyDto> companies) throws IOException {
        for (CompanyDto company : companies) {
            if (company.getPicture() != null) {
                Path path = Paths.get(company.getPicture());
                Resource resource = new InputStreamResource(Files.newInputStream(path));
                InputStream inputStream = resource.getInputStream();
                byte[] bytes = inputStream.readAllBytes();
                String picture = Base64.encodeBase64String(bytes);
                company.setPicture(picture);
            }
        }
    }

    public ResponseFindWorkerDto findWorker(String workerId) {
        Member member = memberRepository.findByUserId(workerId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        return new ResponseFindWorkerDto(member.getUserId(), member.getName(), member.getBirth());
    }

    public ResponseCompanyMainDto companyMain(Long companyId) {
        Company findCompany = companyRepository.findByCompanyMain(companyId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사 입니다."));

        String companyName = findCompany.getName();// 이름
        String employerName = findCompany.getMember().getName();// 사장
        List<IdAndName> workersIdAndName = new ArrayList<>();
        Collection<JoinCompany> joinCompanies = findCompany.getJoinCompanies();
        for (JoinCompany joinCompany : joinCompanies) {
            workersIdAndName.add(new IdAndName(joinCompany.getMember().getId(),
                    joinCompany.getMember().getName()));
        }
        return new ResponseCompanyMainDto(findCompany.getId(), findCompany.getMember().getId(),
                companyName, employerName, workersIdAndName);
    }

    public Member inviteWorker(Long id, RequestInviteWorkerDto request) {

        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 이용자 입니다."));

        if (member.getRole() == Role.ROLE_WORKER) {
            Company company = companyRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

            joinCompanyRepository.save(JoinCompany.builder()
                    .member(member)
                    .company(company)
                    .accept(Accept.NOT_ACCEPT)
                    .build());
        } else {
            throw new RuntimeException("근로자가 아닙니다.");
        }
        return member;
    }

    public Member memberInfo(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재 하지 않는 회원 입니다."));
    }
}
