package albaAyo.albaayo.domains.commute.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.commute.Commute;
import albaAyo.albaayo.domains.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.domains.commute.dto.RequestUpdateCommuteDto;
import albaAyo.albaayo.domains.commute.dto.ResponseCommuteListDto;
import albaAyo.albaayo.domains.commute.dto.ResponsePayInformationDto;
import albaAyo.albaayo.domains.commute.repository.CommuteRepository;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuteService {

    private final FcmService fcmService;
    private final CommuteRepository commuteRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    public void goToWork(RequestCommuteDto request) throws ExecutionException, InterruptedException {

            Commute findCommute = commuteRepository.commute(request.getWorkerId(), request.getCompanyId());
            commuteGeneration(request, findCommute);
    }

    private void commuteGeneration(RequestCommuteDto request, Commute findCommute) throws ExecutionException, InterruptedException {
        if (findCommute == null || findCommute.getEndTime() != null) {
            Member member = memberRepository.findById(request.getWorkerId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회원 입니다."));

            Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));

            Commute commute = Commute.builder().member(member).company(company).
                    startTime(LocalDateTime.now()).build();

            commuteRepository.save(commute);

            fcmNotification(member, company, "출근");
        } else {
            throw new RuntimeException("퇴근을 하지 않았습니다.");
        }
    }

    private void fcmNotification(Member member, Company company, String state) throws ExecutionException, InterruptedException {
        String fcmBody = member.getName() + "님이 " + state + "하셨습니다.";
        fcmService.sendMessage(company.getMember().getFcmToken(), company.getName(), fcmBody);
    }

    public void offWork(RequestCommuteDto request) throws ExecutionException, InterruptedException {

            Commute commute = commuteRepository.commute(request.getWorkerId(), request.getCompanyId());
            if (commute != null && commute.getEndTime() == null) {
                commute.offWorkTime(LocalDateTime.now());

                Member member = memberRepository.findById(request.getWorkerId()).orElseThrow(
                        () -> new RuntimeException("존재하지 않는 회원 입니다."));
                Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(
                        () -> new RuntimeException("존재하지 않는 회사 입니다."));
                fcmNotification(member, company, "퇴근");
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
        return commuteTimeToString(commutes, list);
    }

    public List<ResponseCommuteListDto> commuteTimeToString(List<Commute> commutes, List<ResponseCommuteListDto> list) {
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
        return list;
    }

    public ResponsePayInformationDto monthPayInfo(Long workerId, Long companyId, String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalDateTime time = localDate.atStartOfDay();
        List<Commute> commutes = commuteRepository.monthCommuteList(workerId, companyId, time);
        return new ResponsePayInformationDto(commutes.get(0).payCalculation(commutes));
    }
}
