package albaAyo.albaayo.commute;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Commute{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private LocalDateTime startTime;

    private LocalDateTime EndTime;
}
