package albaAyo.albaayo.member.worker.dto;

import albaAyo.albaayo.config.auth.dto.SessionEmployer;
import albaAyo.albaayo.member.Member;
import albaAyo.albaayo.member.Role;
import lombok.Data;

@Data
public class WorkerDto {

    private Long id;
    private String email;
    private String name;
    private String picture;
    private String birth;
    private Role role;

    public WorkerDto(Member member) {
        role = Role.WORKER;
    }
}
