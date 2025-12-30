package com.jartiste.stockvisionai.infrastructure.config;

import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import com.jartiste.stockvisionai.infrastructure.service.AutheticatedUser;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
@Profile("prod")
public class KeycloakAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UserRepository userRepository;


    public KeycloakAuthConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String email = jwt.getClaimAsString("email");

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        AutheticatedUser authUser = new AutheticatedUser(user);
        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();

        return new UsernamePasswordAuthenticationToken(authUser, jwt, authorities);
    }
}
