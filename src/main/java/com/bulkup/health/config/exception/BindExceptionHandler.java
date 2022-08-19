package com.bulkup.health.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BindExceptionHandler {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Error bindException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }
    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error();
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
    @Getter @Setter
    static class Error {
        private final LocalDateTime timestamp = LocalDateTime.now();
        @JsonProperty("errorCode")
        private final String ERROR_CODE = "C00";
        @JsonProperty("message")
        private final String MESSAGE = "validation error";
        private final List<FieldError> detail;

        Error() {
            this.detail = new ArrayList<>();
        }
        public void addFieldError(String objName, String path, String message) {
            FieldError error = new FieldError(objName, path, message);
            this.detail.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return this.detail;
        }
    }
}
