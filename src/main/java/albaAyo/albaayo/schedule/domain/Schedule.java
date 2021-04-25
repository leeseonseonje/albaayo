package albaAyo.albaayo.schedule.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    private Integer startTime;

    private Integer endTime;

    private String part;

    private String workSchedule;
}
