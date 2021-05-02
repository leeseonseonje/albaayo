package albaAyo.albaayo.member;

import albaAyo.albaayo.BaseTimeEntity;
import albaAyo.albaayo.member.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
public abstract class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, updatable = false)
    private String email;

    @Column(updatable = false)
    private String birth;

    private String name;

    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(Long id, String email, String name, String picture, String birth, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        this.role = role;
    }

    public Member(String email, String birth, String name, String picture, Role role) {
        this.email = email;
        this.birth = birth;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Worker changeWorker(Member member) {
        return new Worker(member.getEmail(),
                member.getName(), member.getPicture(), member.getBirth(), Role.WORKER);
    }
}
