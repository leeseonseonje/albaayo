package albaAyo.albaayo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestScheduleDto {

    private Long id;

    @Size(max = 200, message = "최대 200자 입니다.")
    @NotBlank(message = "내용을 입력해 주세요.")
    private String workSchedule;

    @Max(value = 5)
    @NotBlank(message = "날짜를 확인해 주세요.")
    private Integer date;
}
