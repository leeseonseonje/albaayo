package albaAyo.albaayo.domains.company.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RequestInviteWorkerDto {

    @NotBlank(message = "ID를 입력해 주세요.")
    private String userId;
}
