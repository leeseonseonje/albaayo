package albaAyo.albaayo.domains.location.controller;

import albaAyo.albaayo.domains.location.Location;
import albaAyo.albaayo.domains.location.dto.LocationDto;
import albaAyo.albaayo.domains.location.dto.LocationSaveDto;
import albaAyo.albaayo.domains.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    //위치 조회
    @GetMapping("/location/{workerId}/{companyId}")
    public LocationDto location(@PathVariable Long workerId, @PathVariable Long companyId) {
        Location location = locationService.location(workerId, companyId);
        return LocationDto.builder().location(location.getDepartureLocation()).build();
    }

    //위치 저장(출근)
    @PostMapping("/location")
    public void saveLocation(@RequestBody LocationSaveDto locationSaveDto) {
        locationService.saveLocation(locationSaveDto);
    }

    //위치 삭제(퇴근)
    @DeleteMapping("/location/{workerId}")
    public void deleteLocation(@PathVariable Long workerId) {
        locationService.deleteLocation(workerId);
    }
}
