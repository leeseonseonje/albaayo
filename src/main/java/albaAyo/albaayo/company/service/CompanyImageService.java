package albaAyo.albaayo.company.service;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.dto.CompanyDto;
import albaAyo.albaayo.company.dto.RequestCompanyDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyImageService {

    public static final String URL = "/home/ec2-user/groupImage/";

    public Company companyBuilder(RequestCompanyDto requestCreatCompanyDto) throws IOException {
        Company company = Company.builder()
                .name(requestCreatCompanyDto.getName())
                .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                .location(requestCreatCompanyDto.getLocation())
                .build();
        if (requestCreatCompanyDto.getPicture() != null) {
            String url = imageUpload(requestCreatCompanyDto);
            company.companyPictureSetting(url);
        }
        return company;
    }

    public String imageUpload(RequestCompanyDto requestCreatCompanyDto) throws IOException {
        byte[] bytes = Base64.decodeBase64(requestCreatCompanyDto.getPicture());
        String url = URL + UUID.randomUUID().toString() + ".jpeg";
        FileImageOutputStream image = new FileImageOutputStream(
                new File(url));
        image.write(bytes, 0, bytes.length);
        image.close();
        return url;
    }

    public void imageDownload(List<CompanyDto> companies) throws IOException {
        for (CompanyDto company : companies) {
            if (company.getPicture() != null) {
                Path path = Paths.get(company.getPicture());
                Resource resource = new InputStreamResource(Files.newInputStream(path));
                String picture = Base64.encodeBase64String(resource.getInputStream().readAllBytes());
                company.setPicture(picture);
            }
        }
    }
}
