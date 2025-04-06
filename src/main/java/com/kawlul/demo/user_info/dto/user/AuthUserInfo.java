package com.kawlul.demo.user_info.dto.user;

import com.kawlul.demo.user_info.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class AuthUserInfo {
    private String email;
    private String password;

    public static AuthUserInfo from(User user){
        return  AuthUserInfo.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public void encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
    }
}
