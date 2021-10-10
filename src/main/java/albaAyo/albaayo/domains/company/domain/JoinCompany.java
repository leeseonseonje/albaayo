package albaAyo.albaayo.domains.company.domain;

import albaAyo.albaayo.domains.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class JoinCompany {

    @Id
    @GeneratedValue
    @Column(name = "join_company__id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Accept accept;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public JoinCompany(Member member, Company company, Accept accept) {
        this.member = member;
        this.company = company;
        this.accept = accept;
    }
}
