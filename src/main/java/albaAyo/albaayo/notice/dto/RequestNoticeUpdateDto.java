package albaAyo.albaayo.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoticeUpdateDto {

    private Long noticeId;
    private String title;
    private String contents;
    private List<NoticeImageDto> imageList;
}
