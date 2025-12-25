package com.jartiste.stockvisionai.infrastructure.seeder;

import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.enums.Role;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        seedAdmin();
    }

    private void seedAdmin() {
        Optional<User> adminAccount = userRepository.findByEmail("admin@StockVision.com");
        if (adminAccount.isEmpty()) {

            User admin = User.builder()
                    .nom("ADMIN")
                    .email("admin@StockVision.com")
                    .password(passwordEncoder.encode("Admin1234"))
                    .isActivated(true)
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            log.info("Admin user created");
        }


}}
