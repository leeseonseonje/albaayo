package albaAyo.albaayo.chat.service;

import albaAyo.albaayo.chat.Chat;
import albaAyo.albaayo.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.chat.repository.ChatRepository;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    //채팅 내용 저장 (memberId, companyId, time, content)
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
