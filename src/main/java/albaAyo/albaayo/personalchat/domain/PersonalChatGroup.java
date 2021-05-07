package albaAyo.albaayo.personalchat.domain;

import albaAyo.albaayo.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PersonalChatGroup{

    @Id
    @GeneratedValue
    private Long id;
}
