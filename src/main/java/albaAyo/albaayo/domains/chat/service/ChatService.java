package albaAyo.albaayo.domains.chat.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.chat.domain.Chat;
import albaAyo.albaayo.domains.chat.dto.RequestChattingMessage;
import albaAyo.albaayo.domains.chat.dto.ResponseChatMessage;
import albaAyo.albaayo.domains.chat.repository.ChatRepository;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final FcmService fcmService;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    //채팅 내용 저장
    public void saveChatAndNotification(RequestChattingMessage request) throws ExecutionException, InterruptedException {

        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));
        Company findCompany = companyRepository.findById(request.getCompanyId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회사입니다."));
        Chat chat = Chat.builder().member(findMember)
                .company(findCompany).chatContents(request.getMessage()).build();
        chatRepository.save(chat);
        fcmNotification(findCompany, request);
    }

    //fcm push 알림
    private void fcmNotification(Company company, RequestChattingMessage message) throws ExecutionException, InterruptedException {

        List<String> fcmTokens = joinCompanyRepository.companyWorkers(message.getCompanyId(), message.getMemberId());
        if (!company.getMember().getId().equals(message.getMemberId())) {
            fcmTokens.add(company.getMember().getFcmToken());
        }

        String fcmBody = message.getName() + " : " + message.getMessage();
        fcmService.sendMulticastMessage(fcmTokens, company.getName(), fcmBody);
    }

    //채팅 내용 조회
    public List<ResponseChatMessage> chatContents(Long companyId) {
        return chatRepository.chatContents(companyId);
    }
}
