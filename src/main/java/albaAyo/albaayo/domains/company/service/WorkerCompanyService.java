package albaAyo.albaayo.domains.company.service;

import albaAyo.albaayo.config.fcm.FcmService;
import albaAyo.albaayo.domains.company.domain.Accept;
import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.dto.CompanyDto;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.company.repository.JoinCompanyRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerCompanyService {

    private final FcmService fcmService;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final JoinCompanyRepository joinCompanyRepository;

    public List<Company> acceptCompanyList(Long workerId, Accept accept) throws IOException {
        List<Company> companies = joinCompanyRepository.acceptCompanyList(workerId, accept);
        for (Company company : companies) {
            company.imageDownload(company);
        }
        return companies;
    }

    public Long notAcceptCompanyCount(Long workerId) {
        return joinCompanyRepository.notAcceptCompanyCount(workerId);
    }

    public List<Company> notAcceptCompanyList(Long workerId, Accept accept) {
        return joinCompanyRepository.acceptCompanyList(workerId, accept);
    }

    public void acceptCompany(Long workerId, Long companyId) throws ExecutionException, InterruptedException {
        joinCompanyRepository.companyAccept(workerId, companyId);
        fcmNotification(workerId, companyId, "ACCEPT");
    }

    public void notAcceptCompany(Long workerId, Long companyId) throws ExecutionException, InterruptedException {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
        fcmNotification(workerId, companyId, "NOT_ACCEPT");
    }

    //그룹 퇴장
    public void companyExit(Long workerId, Long companyId) throws ExecutionException, InterruptedException {
        joinCompanyRepository.joinCompanyDelete(workerId, companyId);
        fcmNotification(workerId, companyId, "EXIT");
    }

    private void fcmNotification(Long workerId, Long companyId, String state) throws ExecutionException, InterruptedException {
        Member member = memberRepository.findById(workerId).orElseThrow(() -> new RuntimeException(""));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException(""));

        List<String> fcmTokens = joinCompanyRepository.companyWorkers(company.getId(), member.getId());
        fcmTokens.add(company.getMember().getFcmToken());

        if (state.equals("ACCEPT")) {
            String fcmBody = member.getName() + "님이 " + "함께 일하게 되었습니다.";
            fcmService.sendMulticastMessage(fcmTokens, company.getName(), fcmBody);
        } else if (state.equals("NOT_ACCEPT")) {
            String fcmBody = member.getName() + "님이 " + "초대를 거절하셨습니다.";
            fcmService.sendMessage(company.getMember().getFcmToken(), company.getName(), fcmBody);
        } else {
            String fcmBody = member.getName() + "님이 " + "퇴장하셨습니다.";
            fcmService.sendMulticastMessage(fcmTokens, company.getName(), fcmBody);
        }
    }
}
