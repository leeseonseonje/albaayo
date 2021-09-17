package albaAyo.albaayo.domains.company.dto;

import albaAyo.albaayo.domains.company.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    public CompanyDto(Company company) {
        this.companyId = company.getId();
        this.name = company.getName();
        this.location = company.getLocation();
        this.picture = company.getPicture();
    }
}
