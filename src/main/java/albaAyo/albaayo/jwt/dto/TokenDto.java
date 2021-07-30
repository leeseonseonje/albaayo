package albaAyo.albaayo.jwt.dto;

import albaAyo.albaayo.company.domain.Accept;
import albaAyo.albaayo.member.domain.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private Long id;
    private String userId;
    private String name;
    private Role role;
}
