package albaAyo.albaayo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestScheduleDto {

    private Long id;
    private String workSchedule;
    private Integer date;
}
