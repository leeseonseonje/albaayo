package albaAyo.albaayo.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoticeUpdateDto {

    private Long noticeId;
    private String title;
    private String contents;
    private String image;
}
