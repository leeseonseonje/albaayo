package albaAyo.albaayo.schedule.domain;

import albaAyo.albaayo.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Calendar extends BaseTimeEntity {

    @Id
    private Integer date;
}
