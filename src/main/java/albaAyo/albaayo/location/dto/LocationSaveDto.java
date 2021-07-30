package albaAyo.albaayo.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationSaveDto {

    private Long memberId;
    private Long companyId;
    private String location;
}
