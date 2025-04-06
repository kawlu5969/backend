package com.kawlul.demo.user_info.entity;

import com.kawlul.demo.user_info.dto.user.UserInfo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    private User(String email,String password,String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User of(UserInfo userInfo){
        return User.builder()
                .email(userInfo.getAuthUserInfo().getEmail())
                .password(userInfo.getAuthUserInfo().getPassword())
                .name(userInfo.getName())
                .build();
    }
}
