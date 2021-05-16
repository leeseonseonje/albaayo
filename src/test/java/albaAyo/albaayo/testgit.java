//package albaAyo.albaayo;
//
//
//import albaAyo.albaayo.chat.Chat;
//import albaAyo.albaayo.chat.repository.ChatRepository;
//import albaAyo.albaayo.company.domain.Company;
//import albaAyo.albaayo.company.repository.CompanyRepository;
//import albaAyo.albaayo.member.domain.Employer;
//import albaAyo.albaayo.member.domain.Worker;
//import albaAyo.albaayo.member.repository.EmployerRepository;
//import albaAyo.albaayo.member.repository.WorkerRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//
//@SpringBootTest
//public class testgit {
//
//    @Autowired
//    EmployerRepository employerRepository;
//
//    @Autowired
//    CompanyRepository companyRepository;
//
//    @Autowired
//    WorkerRepository workerRepository;
//
//    @Autowired
//    ChatRepository chatRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void t() {
//        Employer employer = new Employer();
//        employer.setName("A");
//        employer.setBusinessRegistrationNumber(231);
//        employer.setEmail("seon9323@naver.com");
//        employerRepository.save(employer);
//
//        Company company = new Company();
//        company.setEmployer(employer);
//        company.setName("gA");
//        companyRepository.save(company);
//
//
//        Worker workerA = new Worker();
//        workerA.setName("workerA");
//        workerA.setCompany(company);
//        workerA.setEmail("dsda");
//        workerRepository.save(workerA);
//
//        Worker workerB = new Worker();
//        workerB.setName("workerB");
//        workerB.setCompany(company);
//        workerB.setEmail("322");
//        workerRepository.save(workerB);
//
//        Worker workerC = new Worker();
//        workerC.setName("workerC");
//        workerC.setCompany(company);
//        workerC.setEmail("czxczxnghsr");
//        workerRepository.save(workerC);
//
//        Worker workerD = new Worker();
//        workerC.setName("workerD");
//        workerC.setEmail("czxczxndasdassdaghsr");
//        workerRepository.save(workerD);
//
//        Worker workerE = new Worker();
//        workerC.setName("workerE");
//        workerC.setEmail("czxczxn22ddghsr");
//        workerRepository.save(workerE);
//
//        Chat chatA = new Chat();
//        chatA.setCompany(company);
//        chatA.setMember(workerA);
//        chatA.setChatContents("A");
//        chatRepository.save(chatA);
//
//        Chat chatAA = new Chat();
//        chatAA.setCompany(company);
//        chatAA.setMember(workerA);
//        chatAA.setChatContents("chatA");
//        chatRepository.save(chatAA);
//
//        Chat chatB = new Chat();
//        chatB.setCompany(company);
//        chatB.setMember(workerB);
//        chatB.setChatContents("B");
//        chatRepository.save(chatB);
//
//        Chat chatBB = new Chat();
//        chatBB.setCompany(company);
//        chatBB.setMember(workerB);
//        chatBB.setChatContents("chatB");
//        chatRepository.save(chatBB);
//
//        Chat chatC = new Chat();
//        chatC.setCompany(company);
//        chatC.setMember(workerC);
//        chatC.setChatContents("C");
//        chatRepository.save(chatC);
//
//        Chat chatCC = new Chat();
//        chatCC.setCompany(company);
//        chatCC.setMember(workerC);
//        chatCC.setChatContents("chatC");
//        chatRepository.save(chatCC);
//
//        Chat chatD = new Chat();
//        chatD.setCompany(company);
//        chatD.setMember(employer);
//        chatD.setChatContents("D");
//        chatRepository.save(chatD);
//
//        Chat chatDD = new Chat();
//        chatDD.setCompany(company);
//        chatDD.setMember(employer);
//        chatDD.setChatContents("chatDD");
//        chatRepository.save(chatDD);
//    }
//}
