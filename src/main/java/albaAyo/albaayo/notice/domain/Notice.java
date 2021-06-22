package albaAyo.albaayo.notice.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.domain.JoinCompany;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.notice.dto.RequestNoticeUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "notice")
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
    public void changeContents(String contents) {
        this.contents = contents;
    }

    public void changeTitle(String title){
        this.title = title;
    }

}
