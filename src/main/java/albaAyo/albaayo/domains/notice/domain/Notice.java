package albaAyo.albaayo.domains.notice.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.notice.dto.NoticeImageDto;
import albaAyo.albaayo.domains.notice.dto.RequestNoticeUpdateDto;
import albaAyo.albaayo.domains.notice.dto.ResponseNoticeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import javax.imageio.stream.FileImageOutputStream;
import javax.persistence.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "notice", cascade = {CascadeType.REMOVE})
    private List<NoticeImage> noticeImages = new ArrayList<>();

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;

    @Column(length = 50, nullable = false)
    private String date;

    @Builder
    public Notice(Company company, Member member, String title, String contents, String date) {
        this.company = company;
        this.member = member;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public void updateNotice(RequestNoticeUpdateDto requestNoticeUpdateDto, String date) {
        this.title = requestNoticeUpdateDto.getTitle();
        this.contents = requestNoticeUpdateDto.getContents();
        this.date = date;
    }
    public void changeTitle(String title){
        this.title = title;
    }

    public ResponseNoticeDto noticeDtoBuilder(Notice notice, List<NoticeImageDto> list) {
        return ResponseNoticeDto.builder().noticeId(notice.getId())
                .memberId(notice.getMember().getId())
                .name(notice.getMember().getName())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .date(notice.getDate())
                .imageList(list).build();
    }

    public List<NoticeImage> imageUpload(List<NoticeImageDto> list, Notice notice, String path) throws IOException {

        List<NoticeImage> result = new ArrayList<>();

        for (NoticeImageDto noticeImageDto : list) {
            byte[] bytes = Base64.decodeBase64(noticeImageDto.getImage());
            UUID uuid = UUID.randomUUID();
            FileImageOutputStream image = new FileImageOutputStream(
                    new File(path + uuid.toString() + ".jpeg"));
            image.write(bytes, 0, bytes.length);
            image.close();
            result.add(NoticeImage.builder().notice(notice)
                    .image(path + uuid.toString() + ".jpeg")
                    .imageContent(noticeImageDto.getImageContent()).build());
        }

        return result;
    }

    public List<NoticeImageDto> imageDownload(List<NoticeImageDto> imageList) throws IOException {
        for (NoticeImageDto noticeImageDto : imageList) {
            Path path = Paths.get(noticeImageDto.getImage());
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            String image = Base64.encodeBase64String(resource.getInputStream().readAllBytes());
            noticeImageDto.setImage(image);
        }
        return imageList;
    }

}
