package albaAyo.albaayo.chat.controller;

import albaAyo.albaayo.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/company")
    public void companyChatting(RequestChattingMessage message) {
        System.out.println("message = " + message.getMessage());

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ResponseChatMessage response = ResponseChatMessage.builder().memberId(message.getMemberId())
                .name(message.getName()).message(message.getMessage()).time(LocalDateTime.now()).build();
        messagingTemplate.convertAndSend("/recv/company/" + message.getCompanyId(), response);

        chatService.saveChat(message);
    }

    @GetMapping("/chat/{companyId}")
    public List<ResponseChatMessage> chatContents(@PathVariable Long companyId) {
        return chatService.chatContents(companyId);
    }
}
