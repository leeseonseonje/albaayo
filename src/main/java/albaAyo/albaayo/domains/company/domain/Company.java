package albaAyo.albaayo.domains.company.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.domains.chat.domain.Chat;
import albaAyo.albaayo.domains.commute.Commute;
import albaAyo.albaayo.domains.company.dto.RequestCompanyDto;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.notice.domain.Notice;
import albaAyo.albaayo.domains.schedule.domain.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Member member;

    @Column(updatable = false, nullable = false, unique = true)
    private String businessRegistrationNumber;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String location;

    @Column(length = 1000)
    private String picture;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<JoinCompany> joinCompanies = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Commute> commutes = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public Company(String businessRegistrationNumber, String name, String location, String picture) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }

    public void employerCreateCompany(Member member) {
        this.member = member;
    }

    public void updateCompany(RequestCompanyDto request, String picture) {
        this.name = request.getName();
        this.location = request.getLocation();
        this.picture = picture;
    }

    public void companyPictureSetting(String picture) {
        this.picture = picture;
    }

    public String imageUpload(MultipartFile multipartFile, String url) throws IOException {
        String path = url + UUID.randomUUID().toString() + ".jpg";
        multipartFile.transferTo(new File(path));
        return path;
    }

    public void imageDownload(Company company) throws IOException {
        if (company.getPicture() != null) {
            Path path = Paths.get(company.getPicture());
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            this.picture = Base64.encodeBase64String(resource.getInputStream().readAllBytes());
        }
    }

    public void imageDelete(String path) throws IOException {
        Path file = Paths.get(path);
        Files.delete(file);
    }
}
