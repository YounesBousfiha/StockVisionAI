package com.jartiste.stockvisionai.infrastructure.filter;


import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.enums.Role;
import com.jartiste.stockvisionai.infrastructure.service.AutheticatedUser;
import com.jartiste.stockvisionai.infrastructure.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;


    public JWTFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(null == authHeader || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authHeader.substring(7);

            if(jwtService.isRefreshToken(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }

            String email = jwtService.extractUsername(jwt);
            String role = jwtService.extractRoles(jwt);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if(jwtService.isTokenValid(jwt)) {
                    User user = createUserFromToken(email, role);
                    AutheticatedUser userDetails = new AutheticatedUser(user);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Check this

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    Object userId = null;
                    if(principal instanceof AutheticatedUser) {
                        userId = ((AutheticatedUser) principal).getUser().getId();
                    }

                    log.error("User {} logged in with JWT token {}", userId, jwt);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set User authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }



    private User createUserFromToken(String email, String role) {
        return User.builder()
                .email(email)
                .role(Role.valueOf(role))
                .build();
    }
}
