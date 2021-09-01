package albaAyo.albaayo.member.controller;

import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.member.dto.*;
import albaAyo.albaayo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 가입(사장님)
    @PostMapping("/employer/signup")
    public CreateMemberResponse employerSignup(@RequestBody @Valid CreateMemberRequest request) {
        return memberService.employerSignup(request);
    }

    //회원 가입(직원)
    @PostMapping("/worker/signup")
    public CreateMemberResponse workerSignup(@RequestBody @Valid CreateMemberRequest request) {
        return memberService.workerSignup(request);
    }

    //ID 중복 체크
    @GetMapping("/duplicate/{userId}")
    public ValidateDuplicateCheckMessage validateDuplicateUserId(@PathVariable("userId") @Valid RequestUserId userId) {
        try {
            memberService.validateDuplicateUserId(userId.getUserId());
            return new ValidateDuplicateCheckMessage("사용 가능한 ID 입니다.");
        } catch (RuntimeException e) {
            return new ValidateDuplicateCheckMessage("이미 사용중인 ID 입니다.");
        }
    }

    //로그인
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginMemberRequest request) {
        return memberService.login(request);
    }

    //로그아웃
    @GetMapping("/logout/{memberId}")
    public void logout(@PathVariable Long memberId) {
        memberService.logout(memberId);
    }

//    @PostMapping(value = "/test/file")
//    public String fileTest(MultipartFile file) throws IOException {
//        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        String filePath = rootPath + "\\" + file.getOriginalFilename();
//        File dest = new File(filePath);
//        file.transferTo(dest); // 파일 업로드 작업 수행
//        return "";
//    }
}

