package com.kawlul.demo.security.service;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.security.dto.SignInRequest;
import com.kawlul.demo.security.dto.SignUpRequest;
import com.kawlul.demo.user_info.dto.user.AuthUserInfo;
import com.kawlul.demo.user_info.dto.user.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @see AuthenticationServiceImpl
 */
@Service
public interface AuthenticationService {
    ApiResponse<JwtAuthenticationResponse> signUp(UserInfo userInfo);
    ApiResponse<JwtAuthenticationResponse> signIn(AuthUserInfo authUserInfo);
}
