package com.kawlul.demo.security.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
}
