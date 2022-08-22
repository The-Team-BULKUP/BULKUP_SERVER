package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void signupTrainerMapping(@Validated AccountDto.Request.SignupTRAINER req) {
        accountService.signupTrainer(req);
    }

    @PostMapping("/signup/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void signupUserMapping(@Validated AccountDto.Request.SignupUSER req) {
        accountService.signupUser(req);
    }

    @PostMapping("/reissue")
    public AccountDto.Response.Token reissueMap(@RequestHeader(AUTHORIZATION_HEADER) String accessToken,
                                     @RequestHeader(REFRESH_TOKEN_HEADER) String refreshToken,
                                     HttpServletRequest request){
        return accountService.reissue(accessToken, refreshToken);
    }

    @GetMapping("/account/me")
    public Account getAccountInfo(@CurrentUserParameter Account account) {
        // TODO: make custom exception if account is null
        return account;
    }

}
