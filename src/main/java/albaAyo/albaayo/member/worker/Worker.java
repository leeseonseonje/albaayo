package albaAyo.albaayo.member.worker;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("worker")
@Getter
@NoArgsConstructor
public class Worker extends Member {

    @Builder
    public Worker(String userId, String password, String email, String name, String birth, Role role) {
        super(userId, password, email, name, birth, role);
    }
}
