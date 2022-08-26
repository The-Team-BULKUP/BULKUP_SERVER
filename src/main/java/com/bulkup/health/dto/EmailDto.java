package com.bulkup.health.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class EmailDto {
    public static class Request {
        @AllArgsConstructor
        @Getter
        public static class SendEmail {
            private MultipartFile image;
            private String toAddress;
        }
    }
}
