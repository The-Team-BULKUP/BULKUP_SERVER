package com.bulkup.health.config.jwt;

import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.config.exception.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ErrorCode.AUTHENTICATION_FAILED.getErrorCode())
                .message(ErrorCode.AUTHENTICATION_FAILED.name())
                .detail(ErrorCode.AUTHENTICATION_FAILED.getMessage())
                .build();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(errorResponse.toString());
    }
}
