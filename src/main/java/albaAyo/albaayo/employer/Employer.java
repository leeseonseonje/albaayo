package albaAyo.albaayo.employer;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Employer extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "employer_id")
    private Long id;

    @Column(unique = true, updatable = false)
    private String email;

    private String name;

    @Column(updatable = false)
    private LocalDate birth;


}
