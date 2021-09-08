package albaAyo.albaayo.domains.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<T> {

    private T data;
    private Long count;
}
