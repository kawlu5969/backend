package com.kawlul.demo.security.service;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.security.dto.SignInRequest;
import com.kawlul.demo.security.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @see AuthenticationServiceImpl
 */
@Service
public interface AuthenticationService {
    ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signUp(SignUpRequest request);
    ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signIn(SignInRequest request);
}
