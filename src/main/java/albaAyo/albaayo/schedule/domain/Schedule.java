package albaAyo.albaayo.schedule.domain;

import albaAyo.albaayo.company.domain.Company;
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
}
