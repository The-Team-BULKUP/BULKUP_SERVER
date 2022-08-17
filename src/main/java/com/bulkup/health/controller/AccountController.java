package com.bulkup.health.controller;

import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.bulkup.health.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.bulkup.health.config.jwt.JwtFilter.REFRESH_TOKEN_HEADER;

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

    @PostMapping("/reissue")
    public AccountDto.Response.Token reissueMap(@RequestHeader(AUTHORIZATION_HEADER) String accessToken,
                                     @RequestHeader(REFRESH_TOKEN_HEADER) String refreshToken,
                                     HttpServletRequest request){
        return accountService.reissue(accessToken, refreshToken);
    }
}
