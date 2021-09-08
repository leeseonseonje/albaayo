package albaAyo.albaayo.domains.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoticeDto {

    private String title;
    private String contents;
    private List<NoticeImageDto> image;
}
