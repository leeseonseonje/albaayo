package albaAyo.albaayo.chat;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.Member;
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
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(updatable = false)
    private String chatContents;

}
