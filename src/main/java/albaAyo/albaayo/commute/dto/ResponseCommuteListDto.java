package albaAyo.albaayo.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommuteListDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime EndTime;
}
