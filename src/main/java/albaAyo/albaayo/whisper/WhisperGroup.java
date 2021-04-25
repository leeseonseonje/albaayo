package albaAyo.albaayo.whisper;

import albaAyo.albaayo.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class WhisperGroup {

    @Id
    @GeneratedValue
    @Column(name = "whisper_group_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
