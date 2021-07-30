package albaAyo.albaayo.chat.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(updatable = false, length = 500)
    private String chatContents;

    @Builder
    public Chat(Company company, Member member, String chatContents) {
        this.company = company;
        this.member = member;
        this.chatContents = chatContents;
    }
}
