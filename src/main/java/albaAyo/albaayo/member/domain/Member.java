package albaAyo.albaayo.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, updatable = false, length = 50, nullable = false)
    private String email;

    @Column(updatable = false, nullable = false)
    private String birth;

    @Column(length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Role role;

    @Builder
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
