package com.bulkup.health.controller;

import com.bulkup.health.dto.EmailDto;
import com.bulkup.health.util.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/email")
public class MailController {
    private final EmailSender emailSender;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmail(@Validated EmailDto.Request.SendEmail req) throws IOException {
        log.info(req.getImage().getOriginalFilename());
        emailSender.send(
                req.getToAddress(),
                "[피티모아] 일정 내보내기가 완료되었습니다.",
                req.getImage()
        );
    }
}
