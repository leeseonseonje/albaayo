package albaAyo.albaayo.domains.member.domain;

import albaAyo.albaayo.domains.company.domain.JoinCompany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, updatable = false, length = 100, nullable = false)
    private String email;

    @Column(updatable = false, nullable = false)
    private String birth;

    @Column(length = 50, nullable = false)
    private String name;

    private String picture;

    @Column(unique = true)
    private String fcmToken;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<JoinCompany> joinCompanies = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String email, String name,
                  String birth, Role role, String picture) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.role = role;
        this.picture = picture;
    }

    public void fcmTokenSetting(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
