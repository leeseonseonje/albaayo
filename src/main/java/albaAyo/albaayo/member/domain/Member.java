package albaAyo.albaayo.member.domain;

import albaAyo.albaayo.company.Company;
import albaAyo.albaayo.member.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
public abstract class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, updatable = false, length = 50)
    private String email;

    @Column(updatable = false, nullable = false)
    private String birth;

    @Column(length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Role role;

    public Member(String userId, String password, String email, String name, String birth, Role role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.role = role;
    }

//    public Member update(String name, String picture) {
//        this.name = name;
//        this.picture = picture;
//        return this;
//    }

//    public Worker changeWorker(Member member) {
//        return Worker.builder()
//                .email(member.getEmail())
//                .name(member.getName())
//                .picture(member.getPicture())
//                .birth(member.getBirth())
//                .role(Role.WORKER)
//                .build();
//    }
}
