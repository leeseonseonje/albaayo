package albaAyo.albaayo.domains.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    private Long companyId;
    private String name;
    private String location;
    private String picture;

    public CompanyDto(Long companyId, String name, String location, String picture) {
        this.companyId = companyId;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }
}
