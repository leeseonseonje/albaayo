package albaAyo.albaayo.member.dto;

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

    public Employer toEmployer(PasswordEncoder passwordEncoder) {
        return Employer.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .birth(birth)
                .role(Role.ROLE_EMPLOYER)
                .build();
    }

    public Worker toWorker(PasswordEncoder passwordEncoder) {
        return Worker.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .birth(birth)
                .role(Role.ROLE_WORKER)
                .build();
    }
}
