package albaAyo.albaayo.domains.chat.controller;

import albaAyo.albaayo.domains.chat.dto.RequestPersonalChatMessage;
import albaAyo.albaayo.domains.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.domains.chat.service.PersonalChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class PersonalChatController {

    private final PersonalChatService personalChatService;
    private final SimpMessageSendingOperations messagingTemplate;

    //1:1채팅 전송 & 저장
    @MessageMapping("/member")
    public void personalChatting(RequestPersonalChatMessage message) throws ExecutionException, InterruptedException {

        System.out.println("message = " + message.getMessage());
        ResponsePersonalChatMessage response = ResponsePersonalChatMessage.builder()
                .sendMemberId(message.getSendMemberId()).recvMemberId(message.getRecvMemberId())
                .name(message.getName()).message(message.getMessage()).time(LocalDateTime.now()).build();

        if (!message.getSendMemberId().equals(message.getRecvMemberId())) {
            messagingTemplate.convertAndSend("/recv/member/"
                    + message.getSendMemberId() + "/" + message.getRecvMemberId(), response);
            messagingTemplate.convertAndSend("/recv/member/"
                    + message.getRecvMemberId() + "/" + message.getSendMemberId(), response);
        } else {
            //내게쓰기
            messagingTemplate.convertAndSend("/recv/member/"
                    + message.getSendMemberId() + "/" + message.getRecvMemberId(), response);
        }
        personalChatService.saveChatAndNotification(message);
    }

    //1:1채팅 내역 조회
    @GetMapping("/chat/{myMemberId}/{memberId}")
    public List<ResponsePersonalChatMessage> chatContents(@PathVariable Long myMemberId,
                                                          @PathVariable Long memberId) {
        return personalChatService.personalChatContents(myMemberId, memberId);
    }
}
