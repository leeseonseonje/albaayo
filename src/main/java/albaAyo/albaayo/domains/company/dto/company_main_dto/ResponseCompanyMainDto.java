package albaAyo.albaayo.domains.company.dto.company_main_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCompanyMainDto {

    private Long companyId;
    private Long employerId;
    private String companyName;
    private String employerName;
    private List<IdAndName> workersIdAndName;

    public ResponseCompanyMainDto(Long companyId, Long employerId, String companyName, String employerName, List<IdAndName> workersIdAndName) {
        this.companyId = companyId;
        this.employerId = employerId;
        this.companyName = companyName;
        this.employerName = employerName;
        this.workersIdAndName = workersIdAndName;
    }
}
