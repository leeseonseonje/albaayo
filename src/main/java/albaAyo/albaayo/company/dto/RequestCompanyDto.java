package albaAyo.albaayo.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestCompanyDto {

    @Size(max = 50, message = "최대 50자 입니다.")
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @Size(max = 100, message = "최대 100자 입니다.")
    @NotBlank(message = "위치를 입력해 주세요.")
    private String location;

    @NotBlank(message = "사업자 번호를 입력해 주세요.")
    private String businessRegistrationNumber;

    private String picture;
}
