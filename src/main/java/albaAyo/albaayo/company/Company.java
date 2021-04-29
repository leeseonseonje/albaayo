package albaAyo.albaayo.company;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.domain.Employer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    private String name;

    private String location;
}
