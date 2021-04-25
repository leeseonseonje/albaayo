package albaAyo.albaayo.whisper;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.employer.Employer;
import albaAyo.albaayo.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Whisper extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "whisper_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    private String whisperHistory;

}
