package albaAyo.albaayo;

import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class CompanyTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void 테스트() {
        Member mem = Member.builder()
                .role(Role.ROLE_WORKER)
                .email("dsa")
                .name("Dsa")
                .password("dsda")
                .birth("dsada")
                .build();

        em.persist(mem);
        System.out.println("zzz");
    }
}
