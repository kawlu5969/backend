package com.kawlul.demo.security.jwt;

import com.kawlul.demo.security.entitiy.User;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(User user);
    boolean validateToken(String token);
}
