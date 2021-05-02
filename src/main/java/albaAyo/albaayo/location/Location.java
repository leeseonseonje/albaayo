package albaAyo.albaayo.location;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Location extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private String departureLocation;
}
