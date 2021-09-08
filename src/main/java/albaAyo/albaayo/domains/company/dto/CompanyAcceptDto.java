package albaAyo.albaayo.domains.company.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyAcceptDto {

    private Long id;
    private String name;
    private String location;

    @QueryProjection
    public CompanyAcceptDto(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
