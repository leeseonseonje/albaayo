package albaAyo.albaayo.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCompanyMainDto {

    private Long id;
    private String companyName;
    private String employerName;
    private List<String> workersName;

    public ResponseCompanyMainDto(Long id, String companyName, String employerName, List<String> workersName) {
        this.id = id;
        this.companyName = companyName;
        this.employerName = employerName;
        this.workersName = workersName;
    }
}
