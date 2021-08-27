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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyFileService {

    public Company companyBuilder(RequestCompanyDto requestCreatCompanyDto) throws IOException {
        Company company;
        if (requestCreatCompanyDto.getPicture() != null) {
            UUID uuid = imageUpload(requestCreatCompanyDto);

            company = Company.builder()
                    .name(requestCreatCompanyDto.getName())
                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                    .location(requestCreatCompanyDto.getLocation())
                    .picture("C:\\Users\\seon\\groupImage\\" + uuid.toString() + ".jpeg")
                    .build();
        } else {
            company = Company.builder()
                    .name(requestCreatCompanyDto.getName())
                    .businessRegistrationNumber(requestCreatCompanyDto.getBusinessRegistrationNumber())
                    .location(requestCreatCompanyDto.getLocation())
                    .build();
        }
        return company;
    }

    public UUID imageUpload(RequestCompanyDto requestCreatCompanyDto) throws IOException {
        byte[] bytes = Base64.decodeBase64(requestCreatCompanyDto.getPicture());
        UUID uuid = UUID.randomUUID();
        FileImageOutputStream image = new FileImageOutputStream(
                new File("C:\\Users\\seon\\groupImage\\" + uuid.toString() + ".jpeg"));
        image.write(bytes, 0, bytes.length);
        image.close();
        return uuid;
    }

    public void imageDownload(List<CompanyDto> companies) throws IOException {
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

}
