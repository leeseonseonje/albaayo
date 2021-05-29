package albaAyo.albaayo.schedule.domain;

import albaAyo.albaayo.company.domain.Company;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(length = 200)
    private String workSchedule;

    private Integer date;

    @Builder
    public Schedule(Company company, String workSchedule, Integer date) {
        this.company = company;
        this.workSchedule = workSchedule;
        this.date = date;
    }

    public void updateSchedule(String workSchedule, Integer date) {
        this.workSchedule = workSchedule;
        this.date = date;
    }
}
