//package albaAyo.albaayo.member;
//
//import albaAyo.albaayo.member.dto.CreateMemberRequest;
//import albaAyo.albaayo.member.dto.CreateMemberResponse;
//import albaAyo.albaayo.member.employer.Employer;
//import albaAyo.albaayo.member.repository.MemberRepository;
//import albaAyo.albaayo.member.service.MemberService;
//import albaAyo.albaayo.member.worker.Worker;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class TestLoginController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/employer")
//    public CreateMemberResponse saveEmployer(@RequestBody CreateMemberRequest request) {
//
//        Employer employer = Employer.builder()
//                .userId(request.getUserId())
//                .name(request.getName())
//                .password(request.getPassword())
//                .build();
//
//        Employer savedEmployer = memberService.saveEmployer(employer);
//
//        return new CreateMemberResponse(savedEmployer.getId(), savedEmployer.getName());
//    }
//
//    @PostMapping("/worker")
//    public CreateMemberResponse saveWorker(@RequestBody CreateMemberRequest request) {
//
//        Worker worker = Worker.builder()
//                .userId(request.getUserId())
//                .name(request.getName())
//                .password(request.getPassword())
//                .build();
//
//        Worker savedWorker = memberService.saveWorker(worker);
//
//        return new CreateMemberResponse(savedWorker.getId(), savedWorker.getName());
//    }
//}
