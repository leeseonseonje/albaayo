package albaAyo.albaayo.domains.company.dto;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import lombok.Getter;
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

    public CompanyDto(JoinCompany joinCompany) {
        this.companyId = joinCompany.getCompany().getId();
        this.name = joinCompany.getCompany().getName();
        this.location = joinCompany.getCompany().getLocation();
        this.picture = joinCompany.getCompany().getPicture();
    }

    public CompanyDto(Company company) {
        this.companyId = company.getId();
        this.name = company.getName();
        this.location = company.getLocation();
        this.picture = company.getPicture();
    }
}
