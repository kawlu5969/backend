package com.kawlul.demo.welcome;


import com.kawlul.demo.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public ResponseEntity<ApiResponse<WelcomeDto>> welcome(){
        return ResponseEntity.ok(ApiResponse.success(new WelcomeDto("your sign in success")));
    }
}
