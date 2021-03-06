package albaAyo.albaayo.domains.location;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Location extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Member member;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(length = 100)
    private String departureLocation;

    @Builder
    public Location(Member member, Company company, String departureLocation) {
        this.member = member;
        this.company = company;
        this.departureLocation = departureLocation;
    }

    public void updateLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }
}
