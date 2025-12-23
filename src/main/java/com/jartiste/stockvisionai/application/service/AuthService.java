package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse register(String username, String password);
    LoginResponse login(String username, String password);
}
