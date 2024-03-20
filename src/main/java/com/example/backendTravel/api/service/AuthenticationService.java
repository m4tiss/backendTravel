package com.example.backendTravel.api.service;

import com.example.backendTravel.api.config.JwtService;
import com.example.backendTravel.api.controller.AuthenticationResponse;
import com.example.backendTravel.api.controller.LoginRequest;
import com.example.backendTravel.api.controller.RegisterRequest;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);
}
