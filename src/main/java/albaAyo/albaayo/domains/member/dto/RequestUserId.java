package albaAyo.albaayo.domains.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserId {

    @Size(max = 50, message = "최대 50자 입니다.")
    @NotBlank(message = "ID를 입력해 주세요.")
    private String userId;
}
