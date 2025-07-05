package com.paw.fund.core.service.bootstrap.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paw.fund.core.service.bootstrap.config.handler.exception.EErrorCode;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PAuthenticationEntryPoint implements AuthenticationEntryPoint {
    ObjectMapper objectMapper;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus unauthorized = HttpStatus.FORBIDDEN;
        PValueResponse<?> errorResponse = PValueResponse
                .error("Không có quyền truy cập.",
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        EErrorCode.NO_AUTHORITY.getCode());
        response.setContentType("application/json");
        response.setStatus(unauthorized.value());
        OutputStream os = response.getOutputStream();
        objectMapper.writeValue(os, errorResponse);

        os.flush();
    }
}
