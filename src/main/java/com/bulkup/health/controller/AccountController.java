package com.bulkup.health.controller;

import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/login")
    public AccountDto.Response.Token loginMap(@Validated AccountDto.Request.Login req) {
        return accountService.login(req);
    }

    @PostMapping("/signup/trainer")
    public AccountDto.Response.SignupTRAINER signupTrainerMapping(@Validated AccountDto.Request.SignupTRAINER req) {
        return accountService.signupTrainer(req);
    }

    @PostMapping("/signup/user")
    public AccountDto.Response.SignupUSER signupUserMapping(@Validated AccountDto.Request.SignupUSER req) {
        return accountService.signupUser(req);
    }
}
