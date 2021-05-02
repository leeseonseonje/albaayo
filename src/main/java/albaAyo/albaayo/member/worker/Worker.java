package albaAyo.albaayo.member.worker;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.config.auth.dto.SessionEmployer;
import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("worker")
@Getter
@NoArgsConstructor
public class Worker extends Member {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Worker(String email, String name, String picture, String birth, Role role) {
        super(email, name, picture, birth, role);
    }
}
