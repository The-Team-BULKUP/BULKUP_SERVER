package com.bulkup.health.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainer")
public class TrainerController {
    @GetMapping("/")
    public String getTrainerInfo() {
        return "Hello";
    }

    @PostMapping("/")
    public String modifyTrainerInfo() {
        return "Hello";
    }
}
