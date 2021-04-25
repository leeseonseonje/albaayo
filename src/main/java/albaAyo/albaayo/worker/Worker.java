package albaAyo.albaayo.worker;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Worker extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "worker_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(unique = true, updatable = false)
    private String email;

    private String name;

    @Column(updatable = false)
    private LocalDate birth;

}
