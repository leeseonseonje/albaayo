package albaAyo.albaayo.domains.chat.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ChatHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessionList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        for (WebSocketSession webSocketSession : sessionList) {
            webSocketSession.sendMessage(new TextMessage("입장"));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        TextMessage textMessage = new TextMessage(payload);

        for (WebSocketSession webSocketSession : sessionList) {
            webSocketSession.sendMessage(textMessage);
        }
    }
}
