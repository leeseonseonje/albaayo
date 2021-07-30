package albaAyo.albaayo.chat.service;

import albaAyo.albaayo.chat.domain.PersonalChat;
import albaAyo.albaayo.chat.dto.RequestPersonalChatMessage;
import albaAyo.albaayo.chat.dto.ResponsePersonalChatMessage;
import albaAyo.albaayo.chat.repository.PersonalChatRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonalChatService {

    private final PersonalChatRepository personalChatRepository;
    private final MemberRepository memberRepository;

    public void saveChat(RequestPersonalChatMessage request) {
        Member sendMember = memberRepository.findById(request.getSendMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));
        Member recvMember = memberRepository.findById(request.getRecvMemberId()).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원입니다."));

        PersonalChat personalChat = PersonalChat.builder()
                .sendMember(sendMember).recvMember(recvMember)
                .chatContent(request.getMessage()).build();
        personalChatRepository.save(personalChat);
    }

    public List<ResponsePersonalChatMessage> personalChatContents(Long myMemberId, Long memberId) {
        return personalChatRepository.personalChatContents(myMemberId, memberId);
    }
}
