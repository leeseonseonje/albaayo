package albaAyo.albaayo.domains.location.service;

import albaAyo.albaayo.domains.company.domain.Company;
import albaAyo.albaayo.domains.company.repository.CompanyRepository;
import albaAyo.albaayo.domains.location.Location;
import albaAyo.albaayo.domains.location.dto.LocationSaveDto;
import albaAyo.albaayo.domains.location.repository.LocationRepository;
import albaAyo.albaayo.domains.member.domain.Member;
import albaAyo.albaayo.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    public Location location(Long workerId, Long companyId) {
        Location location = locationRepository.location(workerId, companyId);
        if (location == null) {
            throw new RuntimeException("근무 중이 아닙니다.");
        } else {
            return location;
        }
    }

    public void saveLocation(LocationSaveDto locationSaveDto) {

       Location location = locationRepository.findByMemberId(locationSaveDto.getMemberId());
        if (location == null) {
            Member findMember = memberRepository.findById(locationSaveDto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회원 입니다."));
            Company findCompany = companyRepository.findById(locationSaveDto.getCompanyId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 회사 입니다."));
            locationRepository.save(Location.builder()
                    .member(findMember)
                    .company(findCompany)
                    .departureLocation(locationSaveDto.getLocation()).build());
        } else {
            location.updateLocation(locationSaveDto.getLocation());
        }
    }

    public void deleteLocation(Long memberId) {
        Location findLocation = locationRepository.findByMemberId(memberId);
        locationRepository.delete(findLocation);
    }
}
