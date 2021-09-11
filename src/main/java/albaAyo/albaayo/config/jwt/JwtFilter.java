package albaAyo.albaayo.config.jwt;

import albaAyo.albaayo.config.jwt.dto.TokenDto;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            String accessToken = resolveAccessToken(request);

            try {
                if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
                    Authentication authentication = tokenProvider.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("Security Context에 '{}' 인증 정보를 저장했습니다", authentication.getName());
                } else {
                    log.debug("유효한 JWT 토큰이 없습니다");
                }



            } catch (ExpiredJwtException e) {
                String id = e.getClaims().getSubject();
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
                    refreshTokenRepository.delete(findRefreshToken);
                    refreshTokenRepository.save(RefreshToken.builder().id(id).token(token.getRefreshToken()).build());
                    log.info("accessToken: {}, refreshToken: {}", token.getAccessToken(), token.getRefreshToken());
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        filterChain.doFilter(request, response);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.hasText(accessToken)) {
            return accessToken;
        }
        return null;
    }
}
