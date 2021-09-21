package albaAyo.albaayo.domains.chat.repository;

import albaAyo.albaayo.domains.chat.dto.ResponsePersonalChatMessage;

import java.util.List;

public interface PersonalChatRepositoryCustom {

    List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId);

//    List<Long> personalChatHistoryCount(Long, )
}
