package albaAyo.albaayo.domains.notice.dto;

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
}
