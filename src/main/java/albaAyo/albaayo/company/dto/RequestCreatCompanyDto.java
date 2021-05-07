package albaAyo.albaayo.company.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class RequestCreatCompanyDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String location;
    @NotEmpty
    private String businessRegistrationNumber;
}
