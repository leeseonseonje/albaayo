package albaAyo.albaayo.company.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RequestInviteWorkerDto {

    @NotBlank(message = "ID를 입력해 주세요.")
    private String userId;
}
