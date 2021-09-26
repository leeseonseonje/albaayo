package albaAyo.albaayo.config.jwt.dto;

import albaAyo.albaayo.domains.member.domain.Member;
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

    public TokenDto toEntity(Member member) {
        return TokenDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .name(member.getName())
                .role(member.getRole())
                .build();
    }
}
