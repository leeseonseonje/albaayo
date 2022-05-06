package albaAyo.albaayo.domains.member.service;

import albaAyo.albaayo.config.jwt.RefreshToken;
import albaAyo.albaayo.config.jwt.RefreshTokenRepository;
import albaAyo.albaayo.config.jwt.TokenProvider;
import albaAyo.albaayo.config.jwt.dto.TokenDto;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.dto.CreateMemberRequest;
import albaAyo.albaayo.domains.member.dto.CreateMemberResponse;
import albaAyo.albaayo.domains.member.dto.LoginMemberRequest;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = createTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .id(authentication.getName())
                .token(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        fcmTokenSetting(request);
        // 5. 토큰 발급
        return tokenDto;
    }

    private void fcmTokenSetting(LoginMemberRequest request) {
        if (request.getFcmToken() != null) {
            Member member = memberRepository.findByUserId(request.getUserId()).orElseGet(Member::new);
            Member fcmToken = memberRepository.findByFcmToken(request.getFcmToken());
            if (fcmToken != (null)) {
                fcmToken.fcmTokenSetting(null);
            }
            member.fcmTokenSetting(request.getFcmToken());
        }
    }

    public void logout(Long memberId) {
        refreshTokenRepository.refreshTokenDelete(memberId.toString());
        Member member = memberRepository.findById(memberId).orElseGet(Member::new);
        member.fcmTokenSetting(null);

    }

    private TokenDto createTokenDto(Authentication authentication) {
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new RuntimeException("없는 ID 입니다."));

        tokenDataSetting(tokenDto, member);
        return tokenDto;
    }

    private void tokenDataSetting(TokenDto tokenDto, Member member) {
        tokenDto.setId(member.getId());
        tokenDto.setUserId(member.getUserId());
        tokenDto.setName(member.getName());
        tokenDto.setRole(member.getRole());
    }
}
