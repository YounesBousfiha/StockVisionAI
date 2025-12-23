package com.jartiste.stockvisionai.infrastructure.service;


import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import com.jartiste.stockvisionai.infrastructure.service.AutheticatedUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new AutheticatedUser(user);
    }
}

