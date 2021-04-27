package albaAyo.albaayo.personalchat.domain;

import albaAyo.albaayo.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PersonalChatGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "personal_chat_group_id")
    private Long id;
}
