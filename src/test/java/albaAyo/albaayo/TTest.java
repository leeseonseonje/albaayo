//package albaAyo.albaayo;
//
//import albaAyo.albaayo.member.Member;
//import albaAyo.albaayo.member.Role;
//import albaAyo.albaayo.member.repository.MemberRepository;
//import albaAyo.albaayo.member.employer.Employer;
//import albaAyo.albaayo.member.worker.Worker;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//@SpringBootTest
//public class TTest {
//
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void test() {
////        Employer employer = new Employer("sdad", "Dsadsa", "dsada", "dsda", Role.EMPLOYER);
////
////        Worker worker = new Worker("worker@naver.com", "worker", "w", "1092", Role.EMPLOYER);
//
////        employerRepository.save(employer);
//
////        Employer e = employerRepository.findByEmail("sdad").get();
////
////        Worker worker = new Worker(e.getEmail(), e.getName(), e.getPicture(), e.getBirth(), Role.WORKER);
////
////        employerRepository.delete(e);
////
////        workerRepository.save(worker);
////
////        Member member = memberRepository.findByEmail("sdad").get();
////
////        System.out.println(member.getName());
////
////        member = employer;
//
////        Member member = employer;
////
////        System.out.println(member.getName());
////
////        memberRepository.save(member);
////
////        Member m = worker;
////
////        memberRepository.save(m);
////        m = new Employer("1", "2", "3", "4", Role.EMPLOYER);
////
////        memberRepository.save(m);
//
//    }
//
//    @Transactional
//    @Test
//    public void 테스트() {
//        Employer employer = new Employer(1L, "dsad", "dasda", "Dasdas", "dasda", Role.EMPLOYER);
////
//        memberRepository.save(employer);
//
//        Member member = memberRepository.findById(1L).orElseGet(Worker::new);
//        memberRepository.delete(member);
//
//        Worker worker = (Worker) member;
//        System.out.println(worker.getName());
////
////
////        member.changeWorker(member);
//    }
//
//    @Test
//    @Transactional
//    public void emTest() {
//        Employer employer = new Employer("21", "321", "#213", "231", Role.EMPLOYER);
//
//        em.persist(employer);
//
////        employer
//    }
//}
