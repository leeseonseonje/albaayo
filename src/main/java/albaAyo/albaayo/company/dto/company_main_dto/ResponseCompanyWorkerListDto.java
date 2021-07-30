package albaAyo.albaayo.company.dto.company_main_dto;

import albaAyo.albaayo.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanyWorkerListDto {

    private Long memberId;
    private String memberName;
    private String memberBirth;
    private Role memberRole;
}
