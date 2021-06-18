package albaAyo.albaayo.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNoticeListDto {

    private Long id;
    private String name;
    private String title;
    private String date;
}
