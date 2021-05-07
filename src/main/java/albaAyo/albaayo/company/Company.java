package albaAyo.albaayo.company;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.employer.Employer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(updatable = false, nullable = false, unique = true)
    private String businessRegistrationNumber;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String location;

    private String picture;

    @Builder
    public Company(String businessRegistrationNumber, String name, String location) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.name = name;
        this.location = location;
    }

    public void employerCreateCompany(Employer employer) {
        this.employer = employer;
    }
}
