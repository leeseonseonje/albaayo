package albaAyo.albaayo.domains.location.dto;

import lombok.AllArgsConstructor;
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
