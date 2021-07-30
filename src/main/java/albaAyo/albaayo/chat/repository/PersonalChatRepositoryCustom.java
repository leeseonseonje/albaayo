package albaAyo.albaayo.chat.repository;

import albaAyo.albaayo.chat.dto.ResponsePersonalChatMessage;

import java.util.List;

public interface PersonalChatRepositoryCustom {

    List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId);
}
