package albaAyo.albaayo.member.service;

import albaAyo.albaayo.jwt.RefreshToken;
import albaAyo.albaayo.jwt.RefreshTokenRepository;
import albaAyo.albaayo.jwt.TokenProvider;
import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.dto.CreateMemberRequest;
import albaAyo.albaayo.member.dto.CreateMemberResponse;
import albaAyo.albaayo.member.dto.LoginMemberRequest;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public CreateMemberResponse employerSignup(CreateMemberRequest request) {

        validateDuplicateUserId(request.getUserId());
        validateDuplicateEmail(request.getEmail());

        Member member = request.toEmployer(passwordEncoder);
        Member savedEmployer = memberRepository.save(member);

        return new CreateMemberResponse(savedEmployer.getId(), savedEmployer.getName());
    }

    public CreateMemberResponse workerSignup(CreateMemberRequest request) {

        validateDuplicateUserId(request.getUserId());
        validateDuplicateEmail(request.getEmail());

        Member member = request.toWorker(passwordEncoder);
        Member savedWorker = memberRepository.save(member);

        return new CreateMemberResponse(savedWorker.getId(), savedWorker.getName());
    }

    public void validateDuplicateUserId(String userId) {
        if (memberRepository.existsByUserId(userId)) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
    }

    public void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 등록되어 있는 이메일입니다.");
        }
    }

    public TokenDto login(LoginMemberRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = createTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .id(authentication.getName())
                .token(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        // 5. 토큰 발급
        return tokenDto;
    }

    public void logout(Long memberId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(memberId.toString())
                .orElseThrow(() -> new RuntimeException("없는 ID 입니다."));
        refreshTokenRepository.delete(refreshToken);
    }

    private TokenDto createTokenDto(Authentication authentication) {
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new RuntimeException("없는 ID 입니다."));

        tokenDto.setId(member.getId());
        System.out.println(member.getId());
        tokenDto.setUserId(member.getUserId());
        tokenDto.setName(member.getName());
        tokenDto.setRole(member.getRole());
        return tokenDto;
    }
}
