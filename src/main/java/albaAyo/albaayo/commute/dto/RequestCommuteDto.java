package albaAyo.albaayo.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCommuteDto {

    private Long workerId;
    private Long companyId;
}
