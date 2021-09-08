package albaAyo.albaayo.domains.chat.domain;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.domains.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class PersonalChat extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "send_member_id")
    private Member sendMember;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recv_member_id")
    private Member recvMember;

    @Column(updatable = false, length = 500)
    private String chatContent;

    @Builder
    public PersonalChat(Member sendMember, Member recvMember, String chatContent) {
        this.sendMember = sendMember;
        this.recvMember = recvMember;
        this.chatContent = chatContent;
    }
}
