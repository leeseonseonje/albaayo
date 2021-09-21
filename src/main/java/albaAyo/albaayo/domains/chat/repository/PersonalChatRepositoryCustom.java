package albaAyo.albaayo.domains.chat.repository;

import albaAyo.albaayo.domains.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;

import java.util.List;

public interface PersonalChatRepositoryCustom {

    List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId);

    List<Long> personalChatHistoryCount(List<ResponseCompanyWorkerListDto> list);
}
