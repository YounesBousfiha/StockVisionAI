package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.AuthService;
import com.jartiste.stockvisionai.presentation.dto.request.LoginRequest;
import com.jartiste.stockvisionai.presentation.dto.request.RegisterRequest;
import com.jartiste.stockvisionai.presentation.dto.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterRequest registerRequest) {
        LoginResponse response = this.authService.register(registerRequest.getEmail(), registerRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

}
