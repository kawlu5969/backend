package com.kawlul.demo.security.entitiy;

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

    public static User of(String email, String password, String name){
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
