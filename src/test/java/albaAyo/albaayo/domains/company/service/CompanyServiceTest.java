package albaAyo.albaayo.domains.company.service;

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

    @Test
    public void 그룹인원목록() throws IOException {
        List<ResponseCompanyWorkerListDto> companyWorkerList = companyRepository.findCompanyWorkerList(6L);
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerList) {
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
        }
        if (companyWorkerList.isEmpty()) {
            companyWorkerList.add(companyRepository.findCompanyEmployer(6L));
        }

        Assertions.assertThat(companyWorkerList.get(0).getMemberId()).isEqualTo(1L);

        List<ResponseCompanyWorkerListDto> companyWorkerListB = companyRepository.findCompanyWorkerList(7L);
        for (ResponseCompanyWorkerListDto responseCompanyWorkerListDto : companyWorkerList) {
            System.out.println("responseCompanyWorkerListDto.getMemberName() = " + responseCompanyWorkerListDto.getMemberName());
        }
        if (companyWorkerListB.isEmpty()) {
            companyWorkerListB.add(companyRepository.findCompanyEmployer(6L));
        }

        Assertions.assertThat(companyWorkerListB.get(0).getMemberId()).isEqualTo(1L);
    }
}