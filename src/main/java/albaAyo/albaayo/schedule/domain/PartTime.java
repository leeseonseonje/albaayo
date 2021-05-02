package albaAyo.albaayo.schedule.domain;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PartTime {

    @Id
    @GeneratedValue
    @Column(name = "part_time_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer startTime;

    private Integer endTime;

    private String part;
}
