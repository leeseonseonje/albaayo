package albaAyo.albaayo.notice;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.notice.dto.RequestNoticeUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String contents;

    private String image;

    private String date;

    @Builder
    public Notice(Company company, Member member, String title, String contents, String image, String date) {
        this.company = company;
        this.member = member;
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.date = date;
    }

    public void updateNotice(RequestNoticeUpdateDto requestNoticeUpdateDto, String date) {
        this.title = requestNoticeUpdateDto.getTitle();
        this.contents = requestNoticeUpdateDto.getContents();
        this.image = requestNoticeUpdateDto.getImage();
        this.date = date;
    }
    public void changeContents(String contents) {
        this.contents = contents;
    }

    public void changeImage(String image) {
        this.image = image;
    }

    public void changeTitle(String title){
        this.title = title;
    }

}
