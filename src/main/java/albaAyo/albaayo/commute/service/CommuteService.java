package albaAyo.albaayo.commute.service;

import albaAyo.albaayo.commute.Commute;
import albaAyo.albaayo.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.commute.dto.RequestUpdateCommuteDto;
import albaAyo.albaayo.commute.dto.ResponseCommuteListDto;
import albaAyo.albaayo.commute.dto.ResponsePayInformationDto;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
            Member member = memberRepository.findById(request.getWorkerId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회원 입니다."));

            Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

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
        Commute findCommute = commuteRepository.findById(request.getId()).orElseGet(Commute::new);

        LocalDateTime updateTime = updateTimeParse(request.getUpdateTime());

        findCommute.goToWorkTime(updateTime);
    }

    public void updateOffWork(RequestUpdateCommuteDto request) {
        Commute findCommute = commuteRepository.findById(request.getId()).orElseGet(Commute::new);

        LocalDateTime updateTime = updateTimeParse(request.getUpdateTime());

        findCommute.offWorkTime(updateTime);
    }

    public LocalDateTime updateTimeParse(String updateTime) {
        return LocalDateTime.parse(updateTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public List<ResponseCommuteListDto> commuteList(Long workerId, Long companyId) {
        List<Commute> commutes = commuteRepository.commuteList(workerId, companyId);
        List<ResponseCommuteListDto> list = new ArrayList<>();
        commuteTimetoString(commutes, list);
        return list;
    }

    private void commuteTimetoString(List<Commute> commutes, List<ResponseCommuteListDto> list) {
        String startTime = "";
        String endTime = "출근중";
        for (Commute commute : commutes) {
            if (commute.getStartTime() != null) {
                startTime = commute.getStartTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 H시m분"));
            }
            if (commute.getEndTime() != null) {
                endTime = commute.getEndTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 H시m분"));
            }
            list.add(ResponseCommuteListDto.builder().id(commute.getId()).startTime(startTime).EndTime(endTime).build());
        }
    }

    public ResponsePayInformationDto monthPayInfo(Long workerId, Long companyId, String date) {
        LocalDateTime time = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Commute> commutes = commuteRepository.monthCommuteList(workerId, companyId, time);
        return new ResponsePayInformationDto(payCalculation(commutes));
    }

//    public ResponsePayInformationDto monthPayInfo(Long workerId, Long companyId, String date) {
//        LocalDateTime time = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
//        List<Commute> commutes = commuteRepository.monthCommuteList(workerId, companyId, time);
//        return new ResponsePayInformationDto(payCalculation(commutes));
//    }

    private int payCalculation(List<Commute> commutes) {
        int sum = 0;
        for (Commute commute : commutes) {
            if (commute.getEndTime() != null) {
                long workingMinutes = ChronoUnit.MINUTES.between(commute.getStartTime(), commute.getEndTime());
                sum += (int) workingMinutes;
            }
        }
        return (sum/10) * (8720/6);
    }
}
