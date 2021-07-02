package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.company.dto.CompanyDto;
import albaAyo.albaayo.company.repository.JoinCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerCompanyService {

    private final JoinCompanyRepository joinCompanyRepository;

    public List<CompanyDto> acceptCompanyList(Long workerId, Accept accept) throws IOException {
        List<CompanyDto> companies = joinCompanyRepository.acceptCompanyList(workerId, accept);
        imageToByte(companies);
        return companies;
    }

    private void imageToByte(List<CompanyDto> companies) throws IOException {
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

    public Long notAcceptCompanyCount(Long workerId) {
        return joinCompanyRepository.notAcceptCompanyCount(workerId);
    }

    public void acceptCompany(Long workerId, Long companyId) {
        joinCompanyRepository.companyAccept(workerId, companyId);
    }

    public void notAcceptCompany(Long workerId, Long companyId) {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
    }

    public List<CompanyDto> notAcceptCompanyList(Long workerId, Accept accept) {
        return joinCompanyRepository.acceptCompanyList(workerId, accept);
    }

    //그룹 퇴장
    public void companyExit(Long workerId, Long companyId) {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
    }
}
