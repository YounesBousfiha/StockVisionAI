package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.enums.Role;
import com.jartiste.stockvisionai.domain.exception.DuplicateResourceException;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import com.jartiste.stockvisionai.infrastructure.service.AutheticatedUser;
import com.jartiste.stockvisionai.infrastructure.service.JwtService;
import com.jartiste.stockvisionai.infrastructure.service.RefreshTokenService;
import com.jartiste.stockvisionai.presentation.dto.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    private final RedisTemplate<String ,Object> redisTemplate ;


    public LoginResponse register(String email ,String password){
         if(userRepository.findByEmail(email).isPresent()){
             throw new DuplicateResourceException("User already exist with Email :" + email);
         }
         User user = User.builder()
                         .email(email)
                 .password(passwordEncoder.encode(password))
                 .isActivated(true)
                 .role(Role.GESTIONNAIRE)
        .build();
        userRepository.save(user);

        String role = user.getRole().toString();
        String accessToken = this.jwtService.generateToken(email, role);
        String refreshToken = this.jwtService.generateRefreshToken(email);

        refreshTokenService.storeRefreshtoken(refreshToken);

  return LoginResponse.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .tokenType(BEARER_TOKEN_TYPE)
          .email(user.getEmail())
          .role(role)
          .build();
    }

    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        AutheticatedUser autheticatedUser = (AutheticatedUser) authentication.getPrincipal();
        User user = autheticatedUser.getUser();

        String role = user.getRole().toString();
        String accessToken = this.jwtService.generateToken(email, role);
        String refreshToken = this.jwtService.generateRefreshToken(user.getEmail());

        refreshTokenService.storeRefreshtoken(refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(BEARER_TOKEN_TYPE)
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
