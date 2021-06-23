package albaAyo.albaayo.notice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNoticeDto {

    private Long noticeId;
    private Long memberId;
    private String name;
    private String title;
    private String contents;
    private String date;
    private List<NoticeImageDto> imageList;

    public ResponseNoticeDto(Long noticeId, Long memberId, String name, String title, String contents, String date) {
        this.noticeId = noticeId;
        this.memberId = memberId;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
