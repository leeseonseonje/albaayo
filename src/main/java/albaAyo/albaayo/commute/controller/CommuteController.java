package albaAyo.albaayo.commute.controller;

import albaAyo.albaayo.commute.dto.RequestCommuteDto;
import albaAyo.albaayo.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CommuteController {

    private final CommuteService commuteService;

    @PostMapping("/commute")
    public void goToWork(@RequestBody RequestCommuteDto request) {
        commuteService.goToWork(request);
    }

    @PatchMapping("/commute")
    public void offWork(@RequestBody RequestCommuteDto request) {
        commuteService.offWork(request);
    }
}
