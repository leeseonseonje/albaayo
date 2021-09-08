package albaAyo.albaayo.domains.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime time;
}
