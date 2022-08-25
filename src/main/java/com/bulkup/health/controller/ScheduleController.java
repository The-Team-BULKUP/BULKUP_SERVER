package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.dto.ScheduleDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/trainer/{trainerId}")
    public List<ScheduleDto.Response.Schedule>  getTrainerScheduleMapping(@PathVariable Long trainerId) {
        return scheduleService.getTrainerSchedule(trainerId);
    }

    @GetMapping("")
    public List<ScheduleDto.Response.Schedule> getUserScheduleMapping(@CurrentUserParameter Account account) {
        return scheduleService.getUserSchedule(account);
    }

    @Secured("ROLE_TRAINER")
    @PostMapping("/party/{partyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createPartyScheduleMapping(@CurrentUserParameter Account account, ScheduleDto.Request.CreatePartySchedule req, @PathVariable Long partyId) {
        scheduleService.createPartySchedule(account, req, partyId);
    }

    @Secured("ROLE_TRAINER")
    @PostMapping("/extra")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTrainerExtraScheduleMapping(@CurrentUserParameter Account account, ScheduleDto.Request.CreateTrainerExtraSchedule req) {
        scheduleService.createTrainerExtraSchedule(account, req);
    }

}
