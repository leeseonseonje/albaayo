package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.domain.Chat;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.chat.dto.ResponsePersonalChatMessage;

import java.util.List;

public interface ChatRepositoryCustom {

    List<Chat> chatContents(Long companyId);
}
