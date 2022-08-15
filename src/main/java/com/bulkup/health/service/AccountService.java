package com.bulkup.health.service;

import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.config.jwt.TokenProvider;
import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.entity.TokenStorage;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.account.Trainer;
import com.bulkup.health.entity.account.User;
import com.bulkup.health.repository.account.AccountRepository;
import com.bulkup.health.repository.account.TrainerRepository;
import com.bulkup.health.repository.account.UserRepository;
import com.bulkup.health.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisUtil redisUtil;
    private final AccountRepository accountRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    @Transactional
    public AccountDto.Response.SignupTRAINER signupTrainer(AccountDto.Request.SignupTRAINER req) {
        // 회원 중복 검사 ( 아이디, 핸드폰 )
        accountRepository.findByUsername(req.getUsername())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.USERNAME_DUPLICATION);
                });
        accountRepository.findByPhone(req.getPhone())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.PHONE_DUPLICATION);
                });

        // 패스워드 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        req.setPassword(encodedPassword);

        // 회원 저장
        Trainer trainer = trainerRepository.save(req.toEntity());
        return AccountDto.Response.SignupTRAINER.of(trainer);
    }

    @Transactional
    public AccountDto.Response.SignupUSER signupUser(AccountDto.Request.SignupUSER req) {
        // 회원 중복 검사 (아이디, 핸드폰, 닉네임)
        accountRepository.findByUsername(req.getUsername())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.USERNAME_DUPLICATION);
                });
        accountRepository.findByPhone(req.getPhone())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.PHONE_DUPLICATION);
                });
        userRepository.findByNickname(req.getNickname())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.NICKNAME_DUPLICATION);
                });

        // 패스워드 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        req.setPassword(encodedPassword);

        // 회원 저장
        User user = userRepository.save(req.toEntity());
        return AccountDto.Response.SignupUSER.of(user);
    }

    public AccountDto.Response.Token login(AccountDto.Request.Login req) {
        // 아이디, 패스워드 검사
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountRepository.findByUsername(req.getUsername())
                .filter(m -> passwordEncoder.matches(req.getPassword(), m.getPassword()))
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account.getUsername(), req.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //토큰 생성및 리턴
        Map<String, String> jwt = tokenProvider.createToken(account.getUsername());
        String accessToken = jwt.get("token");
        String tokenExpired = jwt.get("tokenExpired");
        String refreshToken = tokenProvider.createRefreshToken();

        TokenStorage tokenStorageEntity = TokenStorage.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .username(account.getUsername())
                .build();
        redisUtil.insertTokenToStorage(account.getUsername(), tokenStorageEntity);
        return new AccountDto.Response.Token(accessToken, tokenExpired, refreshToken);
    }
}