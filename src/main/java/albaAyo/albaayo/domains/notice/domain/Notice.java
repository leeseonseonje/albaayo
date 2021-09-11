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
    public void changeContents(String contents) {
        this.contents = contents;
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

}