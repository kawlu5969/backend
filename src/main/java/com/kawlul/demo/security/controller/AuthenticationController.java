package com.kawlul.demo.security.controller;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.security.dto.SignInRequest;
import com.kawlul.demo.security.dto.SignUpRequest;
import com.kawlul.demo.security.service.AuthenticationService;
import com.kawlul.demo.security.service.AuthenticationServiceImpl;
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
    //com.kawlul.demo.security.service 클래스가 bean으로 등록되어 있음.
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signUp(@RequestBody SignUpRequest request){
        System.out.println(request);
        return authenticationService.signUp(request);
    }
    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signIn(@RequestBody SignInRequest request){
        System.out.println(request);
        return authenticationService.signIn(request);
    }
}
