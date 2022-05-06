package albaAyo.albaayo.domains.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Accept {
    ACCEPT("ACCEPT", memberId -> "님이 함께 일하게 되었습니다."),
    NOT_ACCEPT("NOT_ACCEPT", memberId -> "님이 초대를 거절하셨습니다.");

    private String status;
    private Message message;

    public String message(Long memberId) {
        return message.message(memberId);
    }
}

interface Message {

    String message(Long memberId);
}
