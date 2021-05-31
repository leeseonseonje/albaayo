package albaAyo.albaayo.member.dto;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;


@Getter
public class CreateMemberRequest {

    @Size(max = 50, message = "최대 50자 입니다.")
    @NotBlank(message = "ID를 입력해 주세요.")
    private String userId;

    @Size(min = 8, max = 15, message = "8~15자로 입력해 주세요.")
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @Email(message = "이메일의 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "최대 100자 입니다.")
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @Size(max = 50, message = "최대 50자 입니다.")
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "생년월일을 입력해 주세요.")
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
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
