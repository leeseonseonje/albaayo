package albaAyo.albaayo.member.service;

import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.repository.MemberCustomRepository;
import albaAyo.albaayo.member.worker.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberCustomRepository memberCustomRepository;

    public void changeWorker(Member member, Worker worker) {
        memberCustomRepository.deleteAndSave(member, worker);
    }
}
