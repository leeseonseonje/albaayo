package albaAyo.albaayo.domains.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestScheduleDto {

    private Long companyId;

    private Long memberId;

    private String workSchedule;

    private String date;
}
