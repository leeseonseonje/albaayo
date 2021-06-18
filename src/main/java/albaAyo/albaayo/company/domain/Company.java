package albaAyo.albaayo.company.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Member member;

    @Column(updatable = false, nullable = false, unique = true)
    private String businessRegistrationNumber;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String location;

    @Column(length = 1000)
    private String picture;

    @OneToMany(mappedBy = "company")
    private List<JoinCompany> joinCompanies = new ArrayList<>();

    @Builder
    public Company(String businessRegistrationNumber, String name, String location, String picture) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }

    public void employerCreateCompany(Member member) {
        this.member = member;
    }
}
