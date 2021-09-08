package albaAyo.albaayo.domains.schedule.domain;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PartTime {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer startTime;

    private Integer endTime;

    @Column(length = 50)
    private String part;
}
