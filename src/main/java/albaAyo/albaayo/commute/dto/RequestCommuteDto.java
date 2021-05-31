package albaAyo.albaayo.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommuteDto {

    private Long workerId;
    private Long companyId;

    @NotBlank(message = "위치를 입력해 주세요.")
    private String location;
}
