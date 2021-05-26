package albaAyo.albaayo.member;

import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.member.dto.CreateMemberRequest;
import albaAyo.albaayo.member.dto.CreateMemberResponse;
import albaAyo.albaayo.member.dto.LoginMemberRequest;
import albaAyo.albaayo.member.dto.ValidateDuplicateCheckMessage;
import albaAyo.albaayo.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService authService;

    @PostMapping("/employer/signup")
    public CreateMemberResponse employerSignup(@RequestBody CreateMemberRequest request) {
        return authService.employerSignup(request);
    }

    @PostMapping("/worker/signup")
    public CreateMemberResponse workerSignup(@RequestBody CreateMemberRequest request) {
        return authService.workerSignup(request);
    }

    @GetMapping("/duplicate/{userId}")
    public ValidateDuplicateCheckMessage validateDuplicateUserId(@PathVariable("userId") String userId) {
        try {
            authService.validateDuplicateMember(userId);
            return new ValidateDuplicateCheckMessage("사용 가능한 ID 입니다.");
        } catch (RuntimeException e) {
            return new ValidateDuplicateCheckMessage("이미 사용중인 ID 입니다.");
        }
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginMemberRequest request) {
        return authService.login(request);
    }
}

