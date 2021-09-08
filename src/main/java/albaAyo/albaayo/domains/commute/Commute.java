package albaAyo.albaayo.domains.commute;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    public Commute(Member member, Company company, LocalDateTime startTime, LocalDateTime EndTime) {
        this.member = member;
        this.company = company;
        this.startTime = startTime;
        this.endTime = EndTime;
    }


    public void goToWorkTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void offWorkTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int payCalculation(List<Commute> commutes) {
        int sum = 0;
        for (Commute commute : commutes) {
            if (commute.getEndTime() != null) {
                long workingMinutes = ChronoUnit.MINUTES.between(commute.getStartTime(), commute.getEndTime());
                sum += (int) workingMinutes;
            }
        }
        System.out.println("sum = " + sum);
        return (sum/10) * (8720/6);
    }
}
