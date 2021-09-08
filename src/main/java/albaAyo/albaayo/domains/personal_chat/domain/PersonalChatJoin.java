package albaAyo.albaayo.domains.personal_chat.domain;

import albaAyo.albaayo.domains.member.domain.Member;
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
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "personal_chat_group_id")
    private PersonalChatGroup personalChatGroup;
}
