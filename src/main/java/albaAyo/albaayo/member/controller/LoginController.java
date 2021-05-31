package albaAyo.albaayo.member.controller;

import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.member.dto.*;
import albaAyo.albaayo.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService authService;

    //회원 가입
    @PostMapping("/employer/signup")
    public CreateMemberResponse employerSignup(@RequestBody @Valid CreateMemberRequest request) {
        return authService.employerSignup(request);
    }

    //회원 가입
    @PostMapping("/worker/signup")
    public CreateMemberResponse workerSignup(@RequestBody @Valid CreateMemberRequest request) {
        return authService.workerSignup(request);
    }

    //ID 중복 체크
    @GetMapping("/duplicate/{userId}")
    public ValidateDuplicateCheckMessage validateDuplicateUserId(@PathVariable("userId") @Valid RequestUserId userId) {
        try {
            authService.validateDuplicateUserId(userId.getUserId());
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

