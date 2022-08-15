package com.bulkup.health.config.jwt;

import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.config.exception.ErrorResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ErrorCode.ACCESS_DENIED.getErrorCode())
                .message(ErrorCode.ACCESS_DENIED.name())
                .detail(ErrorCode.ACCESS_DENIED.getMessage())
                .build();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(errorResponse.toString());
    }
}
