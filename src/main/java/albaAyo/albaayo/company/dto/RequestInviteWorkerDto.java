package albaAyo.albaayo.company.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestInviteWorkerDto {

    @NotNull
    private String userId;
}
