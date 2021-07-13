package albaAyo.albaayo.chat.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChatMessage {

    private Long memberId;
    private String name;
    private String message;
    private LocalDateTime time;
}
