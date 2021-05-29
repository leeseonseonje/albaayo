package albaAyo.albaayo.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateCommuteDto {

    private Long id;
    private String updateTime;
}
