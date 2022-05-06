package albaAyo.albaayo.domains.chat.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.chat.domain.PersonalChat;
import albaAyo.albaayo.domains.chat.dto.RequestPersonalChatMessage;
import albaAyo.albaayo.domains.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.domains.chat.repository.PersonalChatRepository;
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
public class PersonalChatService {

    private final FcmService fcmService;
    private final MemberRepository memberRepository;
    private final PersonalChatRepository personalChatRepository;

    public void saveChatAndNotification(RequestPersonalChatMessage request) throws ExecutionException, InterruptedException {

        Member sendMember = memberRepository.findById(request.getSendMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));

        Member recvMember = memberRepository.findById(request.getRecvMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));

        PersonalChat personalChat = PersonalChat.builder()
                .sendMember(sendMember).recvMember(recvMember)
                .chatContent(request.getMessage()).build();
        personalChatRepository.save(personalChat);

        fcmNotification(recvMember, request);
    }

    private void fcmNotification(Member recvMember, RequestPersonalChatMessage request)
            throws ExecutionException, InterruptedException {

        fcmService.sendMessage(recvMember.getFcmToken(), request.getName(), request.getMessage());
    }

    public List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId) {
        return personalChatRepository.personalChatContents(myMemberId, memberId);
    }
}
