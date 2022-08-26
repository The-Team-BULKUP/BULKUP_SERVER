package com.bulkup.health.controller;

import com.bulkup.health.dto.TrainerDto;
import com.bulkup.health.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @GetMapping("")
    public TrainerDto.Response.TrainerList getTrainerListByPointMapping(@Validated TrainerDto.Request.TrainerSearch req) {
        return trainerService.getTrainerListByPointMapping(req);
    }
}
