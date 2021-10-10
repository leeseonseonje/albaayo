package albaAyo.albaayo.domains.schedule.domain;

import albaAyo.albaayo.domains.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(length = 200)
    private String workSchedule;

    private String date;

    @Builder
    public Schedule(Company company, String workSchedule, String date) {
        this.company = company;
        this.workSchedule = workSchedule;
        this.date = date;
    }

    public void updateSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }
}
