package albaAyo.albaayo.notice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNoticeDto {

    private Long noticeId;
    private String name;
    private String title;
    private String contents;
    private String image;
    private String date;
}
