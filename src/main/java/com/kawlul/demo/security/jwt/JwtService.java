package com.kawlul.demo.security.jwt;

import com.kawlul.demo.user_info.dto.user.AuthUserInfo;
import com.kawlul.demo.user_info.entity.User;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(AuthUserInfo authUserInfo);
    boolean validateToken(String token);
}
