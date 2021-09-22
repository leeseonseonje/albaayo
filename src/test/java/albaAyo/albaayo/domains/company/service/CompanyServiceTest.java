package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.chat.domain.Chat;
import albaAyo.albaayo.domains.chat.domain.PersonalChat;
import albaAyo.albaayo.domains.chat.repository.ChatRepository;
import albaAyo.albaayo.domains.chat.repository.PersonalChatRepository;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import albaAyo.albaayo.domains.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class CompanyServiceTest {

    @Value("${admin.pw}")
    private String pw;

    @Autowired
    EntityManager em;

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void companyChatHistoryCountTest() {
        Company companyA = companyRepository.findById(6L).get();
        Company companyB = companyRepository.findById(7L).get();
        Company companyC = companyRepository.findById(8L).get();

        Chat companyA_chatA = Chat.builder().chatContents("companyA-chatA").company(companyA).build();

        Chat companyB_chatA = Chat.builder().chatContents("companyB-chatA").company(companyB).build();
        Chat companyB_chatB = Chat.builder().chatContents("companyB-chatB").company(companyB).build();

        Chat companyC_chatA = Chat.builder().chatContents("companyC-chatA").company(companyC).build();
        Chat companyC_chatB = Chat.builder().chatContents("companyC-chatB").company(companyC).build();
        Chat companyC_chatC = Chat.builder().chatContents("companyC-chatC").company(companyC).build();

        chatRepository.save(companyA_chatA);
        chatRepository.save(companyB_chatA);
        chatRepository.save(companyB_chatB);
        chatRepository.save(companyC_chatA);
        chatRepository.save(companyC_chatB);
        chatRepository.save(companyC_chatC);

        Long companyA_count = chatRepository.companyChatHistoryCount(companyA.getId());
        Long companyB_count = chatRepository.companyChatHistoryCount(companyB.getId());
        Long companyC_count = chatRepository.companyChatHistoryCount(companyC.getId());

        System.out.println("companyA_count = " + companyA_count);
        System.out.println("companyB_count = " + companyB_count);
        System.out.println("companyC_count = " + companyC_count);
        assertThat(companyA_count).isEqualTo(1L);
        assertThat(companyB_count).isEqualTo(2L);
        assertThat(companyC_count).isEqualTo(3L);

    }

    @Test
    public void pchCount() {
        Member employerA = memberRepository.findById(1L).get();
        Member workerA = memberRepository.findById(2L).get();
        Member workerB = memberRepository.findById(3L).get();
        Member workerC = memberRepository.findById(4L).get();

        PersonalChat pchA1 = PersonalChat.builder().chatContent("pchA1").sendMember(employerA).recvMember(workerA).build();
        PersonalChat pchA2 = PersonalChat.builder().chatContent("pchA2").sendMember(employerA).recvMember(workerA).build();
        PersonalChat pchA3 = PersonalChat.builder().chatContent("pchA3").sendMember(employerA).recvMember(workerA).build();

        PersonalChat pchB1 = PersonalChat.builder().chatContent("pchB1").sendMember(employerA).recvMember(workerB).build();
        PersonalChat pchB2 = PersonalChat.builder().chatContent("pchB2").sendMember(employerA).recvMember(workerB).build();

        PersonalChat pchC1 = PersonalChat.builder().chatContent("pchC1").sendMember(employerA).recvMember(workerC).build();
        PersonalChat pchC2 = PersonalChat.builder().chatContent("pchC2").sendMember(employerA).recvMember(workerC).build();
        PersonalChat pchC3 = PersonalChat.builder().chatContent("pchC3").sendMember(employerA).recvMember(workerC).build();
        PersonalChat pchC4 = PersonalChat.builder().chatContent("pchC4").sendMember(employerA).recvMember(workerC).build();
        PersonalChat pchC5 = PersonalChat.builder().chatContent("pchC5").sendMember(employerA).recvMember(workerC).build();

        em.persist(pchA1);
        em.persist(pchA2);
        em.persist(pchA3);

        em.persist(pchB1);
        em.persist(pchB2);

        em.persist(pchC1);
        em.persist(pchC2);
        em.persist(pchC3);
        em.persist(pchC4);
        em.persist(pchC5);

        List<ResponseCompanyWorkerListDto> companyWorkerListB = companyRepository.findCompanyWorkerList(1L, 7L);
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerListB) {
            System.out.println("responseCompanyWorkerListDto.getMemberId = " + responseCompanyWorkerListDto.getMemberId());
            System.out.println("responseCompanyWorkerListDto.getChatCount = " + responseCompanyWorkerListDto.getChatCount());
        }
    }

    @Test
    public void 그룹인원목록() {
        List<ResponseCompanyWorkerListDto> companyWorkerList = companyRepository.findCompanyWorkerList(1L, 6L);
        if (companyWorkerList.isEmpty()) {
            companyWorkerList.add(companyRepository.findCompanyEmployer(6L));
        }
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerList) {
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getChatCount());
        }

        assertThat(companyWorkerList.get(0).getMemberId()).isEqualTo(1L);
        System.out.println("=============================================");
        List<ResponseCompanyWorkerListDto> companyWorkerListB = companyRepository.findCompanyWorkerList(1L, 7L);
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerListB) {
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
            System.out.println("responseCompanyWorkerListDto.getChatCount() = " + responseCompanyWorkerListDto.getChatCount());
        }
        if (companyWorkerListB.isEmpty()) {
            companyWorkerListB.add(companyRepository.findCompanyEmployer(6L));
        }
        Long count = chatRepository.companyChatHistoryCount(7L);
        assertThat(count).isEqualTo(0L);
        assertThat(companyWorkerListB.get(0).getMemberId()).isEqualTo(1L);
    }

    @Test
    public void t() {
        Long test = companyRepository.test();
        System.out.println("test = " + test);
    }
}