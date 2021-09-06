package albaAyo.albaayo.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.UrlResource;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String location;
    private String picture;
    private UrlResource urlResource;

    public CompanyDto(Long id, String name, String location, String picture) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.picture = picture;
    }

    public CompanyDto(Long id, String name, String location, UrlResource urlResource) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.urlResource = urlResource;
    }
}
