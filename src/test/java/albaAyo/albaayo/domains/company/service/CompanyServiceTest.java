package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.RequestCompanyDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.domain.Role;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @InjectMocks
    CompanyService companyService;


    @Mock
    CompanyRepository companyRepository;

    @Mock
    MemberRepository memberRepository;

    @Test
    public void 그룹생성() throws IOException {
        Member member = Member.builder()
                .userId("userId")
                .role(Role.ROLE_EMPLOYER)
                .email("22@bb.com")
                .birth("1999.02.20")
                .name("memberA")
                .password("passs")
                .build();
        RequestCompanyDto request = RequestCompanyDto.builder()
                .name("companyA")
                .location("seoul")
                .businessRegistrationNumber("1234567890").build();
        Member savedMember = Mockito.doReturn(Member.class).when(memberRepository).save(member);

        Company company = companyService.EmployerCreateCompany(savedMember.getId(), request);

        assertThat(company.getName()).isEqualTo("companyA");
    }
}