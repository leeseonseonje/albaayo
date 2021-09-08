package albaAyo.albaayo.domains.member.dto;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;

@Getter
public class LoginMemberRequest {

    @NotNull
    private String userId;
    @NotNull
    private String password;


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
