package albaAyo.albaayo.commute.service;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.commute.repository.CommuteRepository;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    public void goToWork(RequestCommuteDto request) {

        Member member = memberRepository.findById(request.getWorkerId()).orElseGet(Member::new);
        Company company = companyRepository.findById(request.getCompanyId()).orElseGet(Company::new);

        Commute commute = Commute.builder().member(member).company(company).
                startTime(LocalDateTime.now()).build();

        commuteRepository.save(commute);
    }

    public void offWork(RequestCommuteDto request) {
        Commute commute = commuteRepository.offWork(request.getWorkerId(), request.getCompanyId());
        if (commute.getEndTime() == null) {
            commute.offWorkTime(LocalDateTime.now());
        } else {
            throw new RuntimeException("출근을 하지 않았습니다.");
        }
    }
}
