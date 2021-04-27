package albaAyo.albaayo.commute;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.domain.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Commute extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "commute_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private LocalDateTime startTime;

    private LocalDateTime EndTime;
}
