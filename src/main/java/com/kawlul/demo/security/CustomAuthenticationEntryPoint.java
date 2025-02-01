package com.kawlul.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawlul.demo.common.dto.ErrorResponse;
import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SecurityErrorType securityErrorType = (SecurityErrorType)request.getAttribute("exception");
        if(securityErrorType ==null){
            exceptionHandler(response, SecurityErrorType.UNKNOWN);
            return;
        }
        exceptionHandler(response, securityErrorType);
    }
    //인증되지 않은 사용자가 요청할 경우 403 return
    public void exceptionHandler(HttpServletResponse response, SecurityErrorType securityErrorType){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setStatus(securityErrorType.getCode());
            String json = new ObjectMapper().writeValueAsString(ErrorResponse.of(securityErrorType));
            response.getWriter().write(json);
            log.error(securityErrorType.getDetails());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
