package albaAyo.albaayo.domains.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChattingMessage {

    private Long memberId;
    private Long companyId;
    private String name;
    private String message;
}
