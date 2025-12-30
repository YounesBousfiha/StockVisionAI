package com.jartiste.stockvisionai;

import com.jartiste.stockvisionai.application.service.implementation.AuthServiceImpl;
import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.enums.Role;
import com.jartiste.stockvisionai.domain.exception.DuplicateResourceException;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import com.jartiste.stockvisionai.infrastructure.service.AutheticatedUser;
import com.jartiste.stockvisionai.infrastructure.service.JwtService;
import com.jartiste.stockvisionai.infrastructure.service.RefreshTokenService;
import com.jartiste.stockvisionai.presentation.dto.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private RefreshTokenService refreshTokenService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;
    private final String EMAIL = "test@example.com";
    private final String PASSWORD = "password123";
    private final String ACCESS_TOKEN = "access.token.jwt";
    private final String REFRESH_TOKEN = "refresh.token.jwt";

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("1")
                .email(EMAIL)
                .password("encodedPassword")
                .role(Role.GESTIONNAIRE)
                .isActivated(true)
                .build();
    }


    @Test
    void register_ShouldReturnLoginResponse_WhenEmailIsUnique() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(PASSWORD)).thenReturn("encodedPassword");
        when(jwtService.generateToken(anyString(), anyString())).thenReturn(ACCESS_TOKEN);
        when(jwtService.generateRefreshToken(anyString())).thenReturn(REFRESH_TOKEN);

        LoginResponse response = authService.register(EMAIL, PASSWORD);


        assertNotNull(response);
        assertEquals(EMAIL, response.getEmail());
        assertEquals(ACCESS_TOKEN, response.getAccessToken());
        assertEquals(REFRESH_TOKEN, response.getRefreshToken());
        assertEquals("Bearer", response.getTokenType());
        assertEquals("GESTIONNAIRE", response.getRole());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(EMAIL, capturedUser.getEmail());
        assertEquals("encodedPassword", capturedUser.getPassword());
        assertEquals(Role.GESTIONNAIRE, capturedUser.getRole());
        assertTrue(capturedUser.isActivated());

        verify(refreshTokenService).storeRefreshtoken(REFRESH_TOKEN);
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {

        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));


        assertThrows(DuplicateResourceException.class, () -> authService.register(EMAIL, PASSWORD));

        verify(userRepository, never()).save(any());
        verify(jwtService, never()).generateToken(anyString(), anyString());
    }



    @Test
    void login_ShouldReturnLoginResponse_WhenCredentialsAreValid() {

        Authentication authentication = mock(Authentication.class);

        AutheticatedUser authenticatedUser = new AutheticatedUser(user);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);

        when(jwtService.generateToken(EMAIL, "GESTIONNAIRE")).thenReturn(ACCESS_TOKEN);
        when(jwtService.generateRefreshToken(EMAIL)).thenReturn(REFRESH_TOKEN);


        LoginResponse response = authService.login(EMAIL, PASSWORD);


        assertNotNull(response);
        assertEquals(ACCESS_TOKEN, response.getAccessToken());
        assertEquals(REFRESH_TOKEN, response.getRefreshToken());
        assertEquals(EMAIL, response.getEmail());


        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        verify(refreshTokenService).storeRefreshtoken(REFRESH_TOKEN);
    }
}