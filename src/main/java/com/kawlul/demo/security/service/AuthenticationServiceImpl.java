package com.kawlul.demo.security.service;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import com.kawlul.demo.common.exception_handler.custom.SecurityRestApiException;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.user_info.dto.user.AuthUserInfo;
import com.kawlul.demo.user_info.dto.user.UserInfo;
import com.kawlul.demo.security.jwt.JwtService;
import com.kawlul.demo.user_info.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ApiResponse<JwtAuthenticationResponse> signUp(UserInfo userInfo) {
        userInfo.getAuthUserInfo().encodePassword(passwordEncoder);
        userService.createUser(userInfo);

        String jwt = jwtService.generateToken(userInfo.getAuthUserInfo());

        JwtAuthenticationResponse data = JwtAuthenticationResponse.of(jwt);
        return ApiResponse.success(data);
    }

    @Override
    public ApiResponse<JwtAuthenticationResponse> signIn(AuthUserInfo authInfo) {
        AuthUserInfo authUserInfo = userService
                .getUserInfoByEmail(authInfo.getEmail())
                .getAuthUserInfo();

        if(!passwordEncoder.matches(authInfo.getPassword(),authUserInfo.getPassword())){
            throw new SecurityRestApiException(SecurityErrorType.PASSWORD_NOT_MATCHES);
        }

        String jwt = jwtService.generateToken(authUserInfo);

        JwtAuthenticationResponse data = JwtAuthenticationResponse.of(jwt);
        return ApiResponse.success(data);
    }
}
