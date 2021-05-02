package albaAyo.albaayo.member.employer;

import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("employer")
@Getter
@NoArgsConstructor
public class Employer extends Member {

    public Employer(Long id, String email, String name, String picture, String birth, Role role) {
        super(id, email, name, picture, birth, role);
    }

    public Employer(String email, String name, String picture, String birth, Role role) {
        super(email, name, picture, birth, role);
    }
}
