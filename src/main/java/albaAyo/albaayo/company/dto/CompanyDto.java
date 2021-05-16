package albaAyo.albaayo.company.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String location;

    public CompanyDto(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
