package com.kawlul.demo.welcome;

import com.kawlul.demo.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/welcome")
public class WelcomeController {
    private final WelcomeService welcomeService;
    @GetMapping("/home")
    private ResponseEntity<ApiResponse<WelcomeDto>> welcome(){
        return welcomeService.welcome();
    }
}
