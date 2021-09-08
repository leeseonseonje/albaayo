package albaAyo.albaayo.domains.personal_chat.domain;

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
