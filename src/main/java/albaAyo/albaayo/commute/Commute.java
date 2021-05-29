package albaAyo.albaayo.commute;

import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Commute{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Commute(Member member, Company company, LocalDateTime startTime) {
        this.member = member;
        this.company = company;
        this.startTime = startTime;
    }

    public void offWorkTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
