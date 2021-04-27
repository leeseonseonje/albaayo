package albaAyo.albaayo.personalchat.domain;

import albaAyo.albaayo.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class PersonalChatHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "personal_chat_history_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "personal_chat_group_id")
    private PersonalChatGroup personalChatGroup;

    private String whisperHistory;

}
