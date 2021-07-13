package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.dto.ResponseChatMessage;

import java.util.List;

public interface ChatRepositoryCustom {

    List<ResponseChatMessage> chatContents(Long companyId);
}
