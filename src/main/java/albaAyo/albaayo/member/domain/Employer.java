package albaAyo.albaayo.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("employer")
@Getter
@NoArgsConstructor
public class Employer extends Member {

    @Column(unique = true, updatable = false)
    private Integer businessRegistrationNumber;
}
