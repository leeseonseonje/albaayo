package albaAyo.albaayo.member.repository;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.worker.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepository {

    private final EntityManager em;

//    public void deleteAndSave(Member member, Worker worker) {
//        em.remove(member);
//        em.flush();
//        em.persist(worker);
//    }
}
