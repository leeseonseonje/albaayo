package albaAyo.albaayo.member;

import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.jwt.dto.TokenRequestDto;
import albaAyo.albaayo.member.dto.CreateMemberRequest;
import albaAyo.albaayo.member.dto.CreateMemberResponse;
import albaAyo.albaayo.member.dto.LoginMemberRequest;
import albaAyo.albaayo.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginMemberRequest request) {
        return authService.login(request);
    }

//    @PostMapping("/reissue")
//    public TokenDto reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return authService.reissue(tokenRequestDto);
//    }
}

