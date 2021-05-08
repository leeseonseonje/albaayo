package albaAyo.albaayo.member.dto;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import albaAyo.albaayo.member.employer.Employer;
import albaAyo.albaayo.member.worker.Worker;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;


@Getter
public class CreateMemberRequest {

    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String birth;

    public Member toEmployer(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .birth(birth)
                .role(Role.ROLE_EMPLOYER)
                .build();
    }

    public Member toWorker(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .birth(birth)
                .role(Role.ROLE_WORKER)
                .build();
    }
}
