package albaAyo.albaayo.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoticeDto {

    private String title;
    private String contents;
    private String image;
}
