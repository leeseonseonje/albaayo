package albaAyo.albaayo.member.service;

import albaAyo.albaayo.jwt.RefreshToken;
import albaAyo.albaayo.jwt.RefreshTokenRepository;
import albaAyo.albaayo.jwt.TokenProvider;
import albaAyo.albaayo.jwt.dto.TokenDto;
import albaAyo.albaayo.jwt.dto.TokenRequestDto;
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

import javax.jdo.annotations.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public CreateMemberResponse employerSignup(CreateMemberRequest request) {

        validateDuplicateMember(request);

        Member member = request.toEmployer(passwordEncoder);
        Member savedEmployer = memberRepository.save(member);

        return new CreateMemberResponse(savedEmployer.getId(), savedEmployer.getName());
    }

    @Transactional
    public CreateMemberResponse workerSignup(CreateMemberRequest request) {

        validateDuplicateMember(request);

        Member member = request.toWorker(passwordEncoder);
        Member savedWorker = memberRepository.save(member);

        return new CreateMemberResponse(savedWorker.getId(), savedWorker.getName());
    }

    public void validateDuplicateMember(CreateMemberRequest request) {
        if (memberRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
    }

    @Transactional
    public TokenDto login(LoginMemberRequest request) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = createTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .token(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());


        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = createTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    private TokenDto createTokenDto(Authentication authentication) {
        TokenDto tokenDto = tokenProvider.createToken(authentication);

        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new RuntimeException("없는 ID 입니다."));

        tokenDto.setId(member.getId());
        tokenDto.setName(member.getName());
        return tokenDto;
    }
}
