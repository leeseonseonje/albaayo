package albaAyo.albaayo.company.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String location;
    private String picture;

    public CompanyDto(Long id, String name, String location, String picture) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }
}
