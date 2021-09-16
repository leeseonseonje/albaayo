package albaAyo.albaayo.config.jwt.dto;

import albaAyo.albaayo.domains.member.domain.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String accessToken;
    private String refreshToken;
    private Long id;
    private String userId;
    private String name;
    private Role role;
}
