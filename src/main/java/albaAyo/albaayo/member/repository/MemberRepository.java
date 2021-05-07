package albaAyo.albaayo.member.repository;

import albaAyo.albaayo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
