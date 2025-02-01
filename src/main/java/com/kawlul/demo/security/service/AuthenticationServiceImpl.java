package com.kawlul.demo.security.service;

import com.kawlul.demo.common.dto.ApiResponse;
import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import com.kawlul.demo.common.exception_handler.custom.SecurityRestApiException;
import com.kawlul.demo.security.dto.JwtAuthenticationResponse;
import com.kawlul.demo.security.dto.SignInRequest;
import com.kawlul.demo.security.dto.SignUpRequest;
import com.kawlul.demo.security.entitiy.User;
import com.kawlul.demo.security.jwt.JwtService;
import com.kawlul.demo.security.jwt.JwtServiceImpl;
import com.kawlul.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @see com.kawlul.demo.common.exception_handler.handler.ControllerExceptionAdvice
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signUp(SignUpRequest request) {
        Optional<User> foundByEmail = userRepository.findUserByEmail(request.getEmail());
        if(foundByEmail.isPresent()){
            throw new SecurityRestApiException(SecurityErrorType.DUPLICATE_EMAIL);
        }

        Optional<User> foundByName = userRepository.findUserByName(request.getName());
        if(foundByName.isPresent()){
            throw new SecurityRestApiException(SecurityErrorType.DUPLICATE_NAME);
        }

        User user = User.of(request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getName());

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse data = JwtAuthenticationResponse.of(jwt);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    /**
     * @see JwtServiceImpl
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(()-> new SecurityRestApiException(SecurityErrorType.USER_NOT_EXISTS));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new SecurityRestApiException(SecurityErrorType.PASSWORD_NOT_MATCHES);
        }

        String jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse data = JwtAuthenticationResponse.of(jwt);
        return ResponseEntity.ok(ApiResponse.<JwtAuthenticationResponse>success(data));
    }
}
