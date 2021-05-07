package albaAyo.albaayo.company.dto;

import lombok.Getter;

@Getter
public class ResponseCreatCompanyDto {

    private Long id;
    private String name;
    private String location;

    public ResponseCreatCompanyDto(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
