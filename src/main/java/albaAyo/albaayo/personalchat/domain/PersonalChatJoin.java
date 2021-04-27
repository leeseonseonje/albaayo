package albaAyo.albaayo.personalchat.domain;

import albaAyo.albaayo.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class PersonalChatJoin {

    @Id
    @GeneratedValue
    @Column(name = "personal_chat_join_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "personal_chat_group_id")
    private PersonalChatGroup personalChatGroup;
}
