package albaAyo.albaayo.domains.member.repository;

import albaAyo.albaayo.domains.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    Member findByFcmToken(String fcmToken);
}
