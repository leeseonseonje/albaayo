package albaAyo.albaayo.domains.company.dto.company_main_dto;

import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanyWorkerListDto {

    private Long memberId;
    private String memberName;
    private String memberBirth;
    private Role memberRole;
    private Long chatCount;

    public ResponseCompanyWorkerListDto(Long memberId, String memberName, String memberBirth, Role memberRole) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberBirth = memberBirth;
        this.memberRole = memberRole;
    }
}
