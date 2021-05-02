package albaAyo.albaayo.config.auth.dto;

import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionEmployer implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String birth;
    private Role role;

    public SessionEmployer(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
        this.birth = member.getBirth();
        this.role = Role.EMPLOYER;
    }
}
