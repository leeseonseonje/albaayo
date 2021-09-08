package albaAyo.albaayo.domains.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCompanyAcceptDto {

    private Long id;

    public RequestCompanyAcceptDto(Long id) {
        this.id = id;
    }
}
