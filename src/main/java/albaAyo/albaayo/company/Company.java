package albaAyo.albaayo.company;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.employer.Employer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(unique = true, updatable = false)
    private Integer businessRegistrationNumber;

    private String name;

    private String location;
}
