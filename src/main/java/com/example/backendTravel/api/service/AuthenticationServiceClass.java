package com.example.backendTravel.api.service;

import com.example.backendTravel.api.config.JwtService;
import com.example.backendTravel.api.controller.AuthenticationResponse;
import com.example.backendTravel.api.controller.LoginRequest;
import com.example.backendTravel.api.controller.RegisterRequest;
import com.example.backendTravel.api.model.Role;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceClass implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user;
        if (request.getPassword().equals("admin")) {
            user = User.builder()
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userImage(request.getUserImage())
                    .role(Role.ADMIN)
                    .build();
        } else {
            user = User.builder()
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userImage(request.getUserImage())
                    .role(Role.USER)
                    .build();
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
