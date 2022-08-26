package com.bulkup.health.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    protected ResponseEntity<ErrorResponse> handleUnexpectedTypeException(final UnexpectedTypeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse("C000", e.getMessage(), "UnexpectedTypeException"));
    }

}
