package com.bulkup.health.config.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String errorCode;
    private final String message;
    private final String detail;

    @Override
    public String toString() {
        return "{" +
                "\"timestamp\":\"" + timestamp + '\"' +
                ", \"errorCode\":\"" + errorCode + '\"' +
                ", \"message\":\"" + message + '\"' +
                ", \"detail\":\"" + detail + '\"' +
                '}';
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(errorCode.getErrorCode())
                        .message(errorCode.name())
                        .detail(errorCode.getMessage())
                        .build()
                );
    }
}
