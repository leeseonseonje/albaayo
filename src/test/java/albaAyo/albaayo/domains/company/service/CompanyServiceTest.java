package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.chat.domain.Chat;
import albaAyo.albaayo.domains.chat.repository.ChatRepository;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.domain.JoinCompany;
import albaAyo.albaayo.domains.company.dto.company_main_dto.ResponseCompanyWorkerListDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public void 그룹인원목록() {
//        List<ResponseCompanyWorkerListDto> companyWorkerList = companyRepository.findCompanyWorkerList(6L);
//        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerList) {
//            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
//        }
//        if (companyWorkerList.isEmpty()) {
//            companyWorkerList.add(companyRepository.findCompanyEmployer(6L));
//        }
//
//        assertThat(companyWorkerList.get(0).getMemberId()).isEqualTo(1L);

        List<ResponseCompanyWorkerListDto> companyWorkerListB = companyRepository.findCompanyWorkerList(7L);
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerListB) {
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
//            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getChatCount());
        }
        if (companyWorkerListB.isEmpty()) {
            companyWorkerListB.add(companyRepository.findCompanyEmployer(6L));
        }

        assertThat(companyWorkerListB.get(0).getMemberId()).isEqualTo(1L);
    }

    @Test
    public void t() {
        Long test = companyRepository.test();
        System.out.println("test = " + test);
    }
}