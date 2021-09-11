package albaAyo.albaayo.config.jwt;

import albaAyo.albaayo.config.jwt.dto.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            String accessToken = resolveAccessToken(request);

            try {
                contextHolder(accessToken);
            } catch (ExpiredJwtException e) {
                String id = e.getClaims().getSubject();
                jwtService.tokenReissue(response, id);
            }
        filterChain.doFilter(request, response);
    }

    private void contextHolder(String accessToken) {
        if (StringUtils.hasText(accessToken) && tokenProvider.validateToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다", authentication.getName());
        } else {
            log.debug("유효한 JWT 토큰이 없습니다");
        }
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.hasText(accessToken)) {
            return accessToken;
        }
        return null;
    }
}
