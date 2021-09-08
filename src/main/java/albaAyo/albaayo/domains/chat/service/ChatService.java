package albaAyo.albaayo.domains.chat.service;

import albaAyo.albaayo.domains.chat.domain.Chat;
import albaAyo.albaayo.domains.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.domains.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.domains.chat.repository.ChatRepository;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    //채팅 내용 저장
    public void saveChat(RequestChattingMessage request) {
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));
        Company findCompany = companyRepository.findById(request.getCompanyId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사입니다."));
        Chat chat = Chat.builder().member(findMember)
                .company(findCompany).chatContents(request.getMessage()).build();
        chatRepository.save(chat);
    }

    //채팅 내용 조회
    public List<ResponseChatMessage> chatContents(Long companyId) {
        return chatRepository.chatContents(companyId);
    }
}
