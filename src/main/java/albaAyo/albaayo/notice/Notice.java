package albaAyo.albaayo.notice;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

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

    private LocalDateTime modifyTime;
}
