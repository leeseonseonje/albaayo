package albaAyo.albaayo.notice;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.Member;
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
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String contents;

    private String image;
}
