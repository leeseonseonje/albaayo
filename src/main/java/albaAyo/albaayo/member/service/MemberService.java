//package albaAyo.albaayo.member.service;
//
//import albaAyo.albaayo.member.domain.Member;
//import albaAyo.albaayo.member.dto.CreateMemberRequest;
//import albaAyo.albaayo.member.employer.Employer;
//import albaAyo.albaayo.member.repository.MemberCustomRepository;
//import albaAyo.albaayo.member.repository.MemberRepository;
//import albaAyo.albaayo.member.worker.Worker;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberCustomRepository memberCustomRepository;
//    private final MemberRepository memberRepository;
////
////    public void changeWorker(Member member, Worker worker) {
////        memberCustomRepository.deleteAndSave(member, worker);
////    }
//
//    public Employer saveEmployer(Employer employer) {
//
//        validateDuplicateMember(employer);
//        return memberRepository.save(employer);
//    }
//
//    public Worker saveWorker(Worker worker) {
//
//        validateDuplicateMember(worker);
//        return memberRepository.save(worker);
//    }
//
//    private void validateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByUserId(member.getUserId());
//        if (!findMembers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//}
