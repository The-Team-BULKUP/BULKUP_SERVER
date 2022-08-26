package com.bulkup.health.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Bean
    private SimpleMailMessage templateMailMessage() {
        return new SimpleMailMessage();
    }

    public void send(String toAddress, String subject, MultipartFile image) throws IOException {
        byte[] byteImage = image.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(byteImage);

        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("alerm@pt-moa.com");
        message.setSubject(subject);
        message.setText("<img src=\"" + encodedString + "\"/>");
        message.setTo(toAddress);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error(e.getMessage());
        }
    }
}
