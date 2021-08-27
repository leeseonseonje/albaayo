package albaAyo.albaayo.chat.controller;

import albaAyo.albaayo.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.chat.dto.RequestPersonalChatMessage;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.chat.service.ChatService;
import albaAyo.albaayo.chat.service.PersonalChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonalChatController {

    private final PersonalChatService personalChatService;
    private final SimpMessageSendingOperations messagingTemplate;

    //채팅 전송?

    @MessageMapping("/member")
    public void personalChatting(RequestPersonalChatMessage message) {

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
            messagingTemplate.convertAndSend("/recv/member/"
                    + message.getSendMemberId() + "/" + message.getRecvMemberId(), response);
        }
        personalChatService.saveChat(message);
    }

    //채팅내역 조회
    @GetMapping("/chat/{myMemberId}/{memberId}")
    public List<ResponsePersonalChatMessage> chatContents(@PathVariable Long myMemberId,
                                                          @PathVariable Long memberId) {
        return personalChatService.personalChatContents(myMemberId, memberId);
    }
}
