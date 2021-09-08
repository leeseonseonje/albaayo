package albaAyo.albaayo.domains.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPersonalChatMessage {

    private Long sendMemberId;
    private Long recvMemberId;
    private String name;
    private String message;
}
