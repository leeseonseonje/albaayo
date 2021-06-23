package albaAyo.albaayo.notice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NoticeImage {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Column(nullable = false)
    private String image;

    @Column(length = 300)
    private String imageContent;

    @Builder
    public NoticeImage(Notice notice, String image, String imageContent) {
        this.notice = notice;
        this.image = image;
        this.imageContent = imageContent;
    }

    public void updateImage(String image, String imageContent) {
        this.image = image;
        this.imageContent = imageContent;
    }
}
