package albaAyo.albaayo.member.employer;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("employer")
@Getter
@NoArgsConstructor
public class Employer extends Member {

    @Builder
    public Employer(String userId, String password, String email, String name, String birth, Role role) {
        super(userId, password, email, name, birth, role);
    }
}
