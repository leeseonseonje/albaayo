package albaAyo.albaayo.domains.company.dto;

import albaAyo.albaayo.domains.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMemberInformationDto {

    private String name;
    private String userId;
    private String birth;
    private Role role;
}
