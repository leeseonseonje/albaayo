package albaAyo.albaayo.company.dto.company_main_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdAndName {
    private Long workerId;
    private String workerName;

    public IdAndName(Long workerId, String workerName) {
        this.workerId = workerId;
        this.workerName = workerName;
    }
}
