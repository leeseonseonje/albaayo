package albaAyo.albaayo.config.jwt;

import albaAyo.albaayo.config.jwt.RefreshToken;
import albaAyo.albaayo.config.jwt.RefreshTokenRepository;
import albaAyo.albaayo.config.jwt.TokenProvider;
import albaAyo.albaayo.config.jwt.dto.TokenDto;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public void tokenReissue(HttpServletResponse response, String id) throws IOException {
        RefreshToken findRefreshToken = refreshTokenRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재 하지 않는 회원 입니다."));
//                String refreshToken = request.getHeader("RefreshToken");
//                if (refreshToken.equals(findRefreshToken.getToken())) {
        if (findRefreshToken != null) {
            Member member = memberRepository.findById(Long.parseLong(id)).orElseThrow(
                    () -> new RuntimeException("dsa"));
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
            TokenDto token = tokenProvider.createToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.setHeader("Authorization", token.getAccessToken());
            findRefreshToken.tokenReissue(token.getRefreshToken());
            log.info("accessToken: {}, refreshToken: {}", token.getAccessToken(), token.getRefreshToken());
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
