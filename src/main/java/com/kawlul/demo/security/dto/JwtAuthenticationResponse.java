package com.kawlul.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String authToken;
    public static JwtAuthenticationResponse of(String authToken){
        return JwtAuthenticationResponse.builder()
                .authToken("Bearer+"+authToken)
                .build();
    }
}
