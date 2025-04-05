package com.kawlul.demo.security.controller;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.security.dto.SignInRequest;
import com.kawlul.demo.security.dto.SignUpRequest;
import com.kawlul.demo.security.service.AuthenticationService;
import com.kawlul.demo.security.service.AuthenticationServiceImpl;
import com.kawlul.demo.user_info.dto.user.AuthUserInfo;
import com.kawlul.demo.user_info.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see AuthenticationServiceImpl
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ApiResponse<JwtAuthenticationResponse> signUp(@RequestBody UserInfo userInfo){
        return authenticationService.signUp(userInfo);
    }

    @PostMapping("/signin")
    public ApiResponse<JwtAuthenticationResponse> signIn(@RequestBody AuthUserInfo authUserInfo){
        return authenticationService.signIn(authUserInfo);
    }
}
