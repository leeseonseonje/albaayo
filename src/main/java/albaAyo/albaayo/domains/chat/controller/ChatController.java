package albaAyo.albaayo.domains.chat.controller;

import albaAyo.albaayo.domains.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.domains.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.domains.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;

    //그룹채팅 전송 & 저장
    @MessageMapping("/company")
    public void companyChatting(RequestChattingMessage message) {

        ResponseChatMessage response = ResponseChatMessage.builder().memberId(message.getMemberId())
                .name(message.getName()).message(message.getMessage()).time(LocalDateTime.now()).build();
        messagingTemplate.convertAndSend("/recv/company/" + message.getCompanyId(), response);

        chatService.saveChat(message);
    }

    //그룹채팅 내역 조회
    @GetMapping("/chat/{companyId}")
    public List<ResponseChatMessage> chatContents(@PathVariable Long companyId) {
        return chatService.chatContents(companyId);
    }
}
