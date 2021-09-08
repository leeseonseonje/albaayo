package albaAyo.albaayo.domains.chat.repository;

import albaAyo.albaayo.domains.chat.dto.ResponseChatMessage;

import java.util.List;

public interface ChatRepositoryCustom {

    List<ResponseChatMessage> chatContents(Long companyId);
}
