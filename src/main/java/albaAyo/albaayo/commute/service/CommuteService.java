package albaAyo.albaayo.commute.service;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.commute.dto.RequestUpdateCommuteDto;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;
import albaAyo.albaayo.commute.repository.CommuteRepository;
import albaAyo.albaayo.company.domain.Company;
import albaAyo.albaayo.company.repository.CompanyRepository;
import albaAyo.albaayo.member.domain.Member;
import albaAyo.albaayo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    public void goToWork(RequestCommuteDto request) {

        Commute findCommute = commuteRepository.commute(request.getWorkerId(), request.getCompanyId());

        commuteGeneration(request, findCommute);
    }

    private void commuteGeneration(RequestCommuteDto request, Commute findCommute) {
        if (findCommute == null || findCommute.getEndTime() != null) {
            Member member = memberRepository.findById(request.getWorkerId()).orElseGet(Member::new);
            Company company = companyRepository.findById(request.getCompanyId()).orElseGet(Company::new);

            Commute commute = Commute.builder().member(member).company(company).
                    startTime(LocalDateTime.now()).build();

            commuteRepository.save(commute);
        } else {
            throw new RuntimeException("퇴근을 하지 않았습니다.");
        }
    }

    public void offWork(RequestCommuteDto request) {
        Commute commute = commuteRepository.commute(request.getWorkerId(), request.getCompanyId());
        if (commute != null && commute.getEndTime() == null) {
            commute.offWorkTime(LocalDateTime.now());
        } else {
            throw new RuntimeException("출근을 하지 않았습니다.");
        }
    }

    public void updateGoToWork(RequestUpdateCommuteDto request) {
        Commute findCommute = commuteRepository.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("null"));

        LocalDateTime updateTime = updateTimeParse(request.getUpdateTime());

        findCommute.goToWorkTime(updateTime);
    }

    public void updateOffWork(RequestUpdateCommuteDto request) {
        Commute findCommute = commuteRepository.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("null"));

        LocalDateTime updateTime = updateTimeParse(request.getUpdateTime());

        findCommute.offWorkTime(updateTime);
    }

    public LocalDateTime updateTimeParse(String updateTime) {
        return LocalDateTime.parse(updateTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public List<ResponseCommuteListDto> commuteList(Long workerId, Long companyId) {
        return commuteRepository.commuteList(workerId, companyId);
    }
}
