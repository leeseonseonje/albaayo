package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.dto.RequestCompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyImageService {

    public static final String URL = "C:\\Users\\seon\\groupImage\\";

    public Company companyBuilder(RequestCompanyDto request) throws IOException {
        Company company = Company.builder()
                .name(request.getName())
                .businessRegistrationNumber(request.getBusinessRegistrationNumber())
                .location(request.getLocation())
                .build();

        log.info("image={}", request.getPicture());
        if (request.getPicture() == null) {
            return company;
        }
        else if (!request.getPicture().isEmpty()) {
            company.companyPictureSetting(imageUpload(request.getPicture()));
        }
        return company;
    }

    public void imageDownload(List<CompanyDto> companies) throws IOException {
        for (CompanyDto company : companies) {
            if (company.getPicture() != null) {
                Path path = Paths.get(URL + company.getPicture() + ".jpg");
                Resource resource = new InputStreamResource(Files.newInputStream(path));
                String picture = Base64.encodeBase64String(resource.getInputStream().readAllBytes());
                company.setPicture(picture);
            }
        }
    }

    public String imageUpload(MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString();
        multipartFile.transferTo(new File(URL + uuid + ".jpg"));
        return uuid;
    }
}
