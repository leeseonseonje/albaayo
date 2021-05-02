package albaAyo.albaayo.member;

import albaAyo.albaayo.config.auth.LoginMember;
import albaAyo.albaayo.config.auth.dto.SessionEmployer;
import albaAyo.albaayo.member.repository.MemberRepository;
import albaAyo.albaayo.member.service.MemberService;
import albaAyo.albaayo.member.worker.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/employer")
    public String employerLogin(@LoginMember SessionEmployer sessionMember) {
        System.out.println(sessionMember.getId());

        return "testEmployer";
    }

    @GetMapping("/worker")
    public String workerLogin(@LoginMember SessionEmployer sessionMember) {

        Member member = memberRepository.findById(sessionMember.getId()).orElseGet(Worker::new);
        Worker worker = member.changeWorker(member);
        memberService.changeWorker(member, worker);

        return "testWorker";
    }
}
