package albaAyo.albaayo.domains.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommuteListDto {

    private Long id;
    private String startTime;
    private String EndTime;
}
